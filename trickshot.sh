#!/bin/bash
# 1. You would need Android SDK, with platform version 27 or later and build tools version 27 or later
# 1-1. You can get Android SDK from: https://developer.android.com/studio/#downloads (In 'Command line tools only')
# 2. You would also need a tool named D8; It is included in build tools 28.x or later of Android SDK (You can install and use 27.x and 28.x in parallel)


# Here
_placeHere=$(pwd)

# Android SDK
# Replace the value with proper directory for you
_placeSDKAndroid="/opt/sdk/android"

# Version of toolchain (${_placeSDKAndroid}/build-tools)
# 28.x or higher version is recommended
_versionToolchain="28.0.3"

# Resources
_listResources=(res)
_optionResources=()
for x in ${_listResources[@]}; do
	_optionResources+=(-S)
	_optionResources+=(${x})
done

_api="27"
_version="8.1.0"
_keystore="${HOME}/keystore.jks"
_key="key1"

_framework="${_placeSDKAndroid}/platforms/android-${_api}/android.jar"


function swansong {
	date >> $TOFFEE/timestamp-brunch
	exit 1
}
function lowercase {
	echo "$1" | tr '[:upper:]' '[:lower:]'
}


if (test "$1" = "build"); then
	echo ":::: Initialize"
	rm -rf assets classes.dex outlet proguard_options public_resources.xml sources-*.txt unfinished.apk Creole.apk || exit 1
	for x in $(find . -name R.java); do
		rm -f $x
	done
	mkdir -p outlet || exit 1
	
	echo ":::: Generate R.java"
	${_placeSDKAndroid}/build-tools/${_versionToolchain}/aapt package --auto-add-overlay -m -J java -M AndroidManifest.xml -P public_resources.xml -G proguard_options --min-sdk-version ${_api} --target-sdk-version ${_api} --version-code ${_api} --version-name ${_version} \
		${_optionResources[@]} \
		-I ${_framework} || exit 1
		
	echo ":::: Compile Java code"
	find java -name '*.java' >> sources-unsorted.txt
	tr ' ' '\n' < sources-unsorted.txt | sort -u > sources-sorted.txt
	javac -d outlet -classpath ${_framework} -sourcepath java @sources-sorted.txt || exit 1
		
	echo ":::: Generate Dalvik executable file"
	${_placeSDKAndroid}/build-tools/${_versionToolchain}/d8 --release $(find outlet -name "*.class") || exit 1
	
	echo ":::: Generate Android package file"
	${_placeSDKAndroid}/build-tools/${_versionToolchain}/aapt package -u --auto-add-overlay -M AndroidManifest.xml --min-sdk-version ${_api} --target-sdk-version ${_api} --version-code ${_api} --version-name ${_version} \
		${_optionResources[@]} \
		-I ${_framework} \
	-F unfinished.apk || exit 1
	
	# Insert Dex
	zip -qj unfinished.apk classes.dex || exit 1
		
	# Sign
	jarsigner -keystore ${_keystore} unfinished.apk ${_key} || exit 1
	
	# Align
	${_placeSDKAndroid}/build-tools/${_versionToolchain}/zipalign -f 4 unfinished.apk Creole.apk || exit 1
fi
