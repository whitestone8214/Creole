/*
	Copyright (C) 2018 Minho Jo <whitestone8214@gmail.com>
	
	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	
	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.
	
	You should have received a copy of the GNU General Public License
	along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


package whitestone8214.creole;

/* Inhouse modules. */
import whitestone8214.creole.DrawHelper;
import whitestone8214.creole.SuperViewGroup;

/* Android modules. */
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.hardware.*;
import android.hardware.display.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.animation.*;
import android.view.inputmethod.*;
import android.view.SurfaceHolder.*;
import android.webkit.*;
import android.widget.*;

/* Java modules. */
import java.io.*;
import java.lang.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;


public class CameraRoom extends Activity {
	/* Interfaces and classes. */
	interface Next {public void action(View _this, int widthThis, int heightThis);}
	interface Looks {public void action(View _this, Canvas canvas);}
	interface Packing {public void action(View _this, boolean changed, int left, int top, int right, int bottom);}
	interface TouchReaction {public boolean action(View _this, MotionEvent motion);}
	
	class EditText2 extends EditText {
		int index;
		String label;
		int width;
		int height;
		Next next;
		Looks looks;
		
		public EditText2(Context context) {
			super(context);
			setWillNotDraw(false);
			index = 0;
			label = null;
			width = 0;
			height = 0;
			next = null;
			looks = null;
		}
		public EditText2 index(int value) {index = value; return this;}
		public EditText2 label(String value) {label = value; return this;}
		public EditText2 width(int value) {width = value; return this;}
		public EditText2 height(int value) {height = value; return this;}
		public EditText2 text(String value) {setText(value); return this;}
		public EditText2 textSize(int value) {setTextSize(TypedValue.COMPLEX_UNIT_PX, value); return this;}
		public EditText2 textColor(int value) {setTextColor(value); return this;}
		public EditText2 alignment(int value) {setGravity(value); return this;}
		public EditText2 oneline(boolean value) {setSingleLine(value); return this;}
		public EditText2 next(Next value) {next = value; return this;}
		public EditText2 looks(Looks value) {looks = value; return this;}
		
		public void onMeasure(int widthNatural, int heightNatural) {
			if ((width <= 0) || (height <= 0)) {
				super.onMeasure(widthNatural, heightNatural);
				if (next != null) next.action(this, widthNatural, heightNatural);
			}
			else {
				setMeasuredDimension(width, height);
				if (next != null) next.action(this, width, height);
			}
		}
		public void onDraw(Canvas canvas) {
			if (looks != null) looks.action(this, canvas);
			super.onDraw(canvas);
		}
	}
	class TextView2 extends TextView {
		int index;
		String label;
		int width;
		int height;
		Next next;
		Looks looks;
		
		public TextView2(Context context) {
			super(context);
			setWillNotDraw(false);
			index = 0;
			label = null;
			width = 0;
			height = 0;
			next = null;
			looks = null;
		}
		public TextView2 index(int value) {index = value; return this;}
		public TextView2 label(String value) {label = value; return this;}
		public TextView2 width(int value) {width = value; return this;}
		public TextView2 height(int value) {height = value; return this;}
		public TextView2 text(String value) {setText(value); return this;}
		public TextView2 whenClicked(View.OnClickListener value) {setOnClickListener(value); return this;}
		public TextView2 next(Next value) {next = value; return this;}
		public TextView2 looks(Looks value) {looks = value; return this;}
		
		public void onMeasure(int widthNatural, int heightNatural) {
			if ((width <= 0) || (height <= 0)) {
				super.onMeasure(widthNatural, heightNatural);
				if (next != null) next.action(this, widthNatural, heightNatural);
			}
			else {
				super.onMeasure(width, height);
				setMeasuredDimension(width, height);
				if (next != null) next.action(this, width, height);
			}
		}
		public void onDraw(Canvas canvas) {
			if (looks != null) looks.action(this, canvas);
			else super.onDraw(canvas);
		}
	}
	class WebView2 extends WebView {
		int index;
		String label;
		int width;
		int height;
		Next next;
		
		public WebView2(Context context) {
			super(context);
			index = 0;
			label = null;
			width = 0;
			height = 0;
			next = null;
			
			
		}
		public WebView2 index(int value) {index = value; return this;}
		public WebView2 label(String value) {label = value; return this;}
		public WebView2 width(int value) {width = value; return this;}
		public WebView2 height(int value) {height = value; return this;}
		public WebView2 next(Next value) {next = value; return this;}
		
		public void onMeasure(int widthNatural, int heightNatural) {
			if ((width <= 0) || (height <= 0)) {
				super.onMeasure(widthNatural, heightNatural);
				if (next != null) next.action(this, widthNatural, heightNatural);
			}
			else {
				super.onMeasure(width, height);
				setMeasuredDimension(width, height);
				if (next != null) next.action(this, width, height);
			}
		}
	}
	class LinearLayout3 extends LinearLayout {
		public int index;
		public String label;
		public boolean fixedSize;
		public int width;
		public int height;
		public int x;
		public int y;
		public int widthInside;
		public int heightInside;
		public int distance;
		public Next next;
		public Looks looks;
		public Packing packing;
		public TouchReaction touchReaction;
		public TouchReaction snatchReaction;
		
		public int xDnDLast;
		public int yDnDLast;
		public int xSlideStart;
		public int ySlideStart;
		
		public LinearLayout3(Context context) {
			super(context);
			setWillNotDraw(false);
			index = 0;
			label = null;
			fixedSize = false;
			width = 0;
			height = 0;
			x = 0;
			y = 0;
			widthInside = 0;
			heightInside = 0;
			distance = 0;
			next = null;
			looks = null;
			packing = null;
			touchReaction = null;
			
			xDnDLast = 0;
			yDnDLast = 0;
			xSlideStart = 0;
			ySlideStart = 0;
		}
		public LinearLayout3 index(int value) {index = value; return this;}
		public LinearLayout3 label(String value) {label = value; return this;}
		public LinearLayout3 fixedSize(boolean value) {fixedSize = value; return this;}
		public LinearLayout3 width(int value) {width = value; return this;}
		public LinearLayout3 height(int value) {height = value; return this;}
		public LinearLayout3 x(int value) {x = value; return this;}
		public LinearLayout3 y(int value) {y = value; return this;}
		public LinearLayout3 widthInside(int value) {widthInside = value; return this;}
		public LinearLayout3 heightInside(int value) {heightInside = value; return this;}
		public LinearLayout3 distance(int value) {distance = value; return this;}
		public LinearLayout3 direction(int value) {setOrientation(value); return this;}
		public LinearLayout3 in(View value) {addView(value); return this;}
		public LinearLayout3 out(int value) {removeViewAt(value); return this;}
		public LinearLayout3 parent() {
			try {return (LinearLayout3)getParent();}
			catch(Exception x) {return null;}
		}
		public LinearLayout3 child(int nth) {
			try {return (LinearLayout3)getChildAt(nth);}
			catch(Exception x) {return null;}
		}
		public LinearLayout3 clear() {removeAllViews(); return this;}
		public LinearLayout3 next(Next value) {next = value; return this;}
		public LinearLayout3 looks(Looks value) {looks = value; return this;}
		public LinearLayout3 packing(Packing value) {packing = value; return this;}
		public LinearLayout3 touchReaction(TouchReaction value) {touchReaction = value; return this;}
		public LinearLayout3 snatchReaction(TouchReaction value) {snatchReaction = value; return this;}
		
		public void onMeasure(int widthNatural, int heightNatural) {
			if ((width <= 0) || (height <= 0)) {
				super.onMeasure(widthNatural, heightNatural);
				if (next != null) next.action(this, widthNatural, heightNatural);
			}
			else {
				super.onMeasure(width, height);
				if (fixedSize == true) setMeasuredDimension(width, height);
				if (next != null) next.action(this, width, height);
			}
		}
		public void onDraw(Canvas canvas) {
			if (looks != null) looks.action(this, canvas);
			super.onDraw(canvas);
		}
		public void onLayout(boolean changed, int left, int top, int right, int bottom) {
			if (packing != null) packing.action(this, changed, left, top, right, bottom);
			else super.onLayout(changed, left, top, right, bottom);
		}
		public boolean onTouchEvent(MotionEvent motion) {
			if (touchReaction != null) return touchReaction.action(this, motion);
			else return super.onTouchEvent(motion);
		}
		public boolean onInterceptTouchEvent(MotionEvent motion) {
			if (snatchReaction != null) return snatchReaction.action(this, motion);
			else return super.onInterceptTouchEvent(motion);
		}
	}
	class SurfaceView2 extends SurfaceView {
		int index;
		String label;
		int width;
		int height;
		Next next;
		Looks looks;
		
		public SurfaceView2(Context context) {
			super(context);
			//setWillNotDraw(false);
			index = 0;
			label = null;
			width = 0;
			height = 0;
			next = null;
			looks = null;
		}
		public SurfaceView2 index(int value) {index = value; return this;}
		public SurfaceView2 label(String value) {label = value; return this;}
		public SurfaceView2 width(int value) {width = value; return this;}
		public SurfaceView2 height(int value) {height = value; return this;}
		public SurfaceView2 next(Next value) {next = value; return this;}
		public SurfaceView2 looks(Looks value) {looks = value; return this;}
		
		public void onMeasure(int widthNatural, int heightNatural) {
			if ((width <= 0) || (height <= 0)) {
				super.onMeasure(widthNatural, heightNatural);
				if (next != null) next.action(this, widthNatural, heightNatural);
			}
			else {
				setMeasuredDimension(width, height);
				if (next != null) next.action(this, width, height);
			}
		}
		/*public void onDraw(Canvas canvas) {
			if (looks != null) looks.action(this, canvas);
			super.onDraw(canvas);
		}*/
	}
	class CompareFiles implements Comparator<File> {
		public int compare(File a, File b) {
			return a.getName().compareTo(b.getName());
		}
	}
	class AnimationScrolling extends Animation {
		public LinearLayout3 host;
		public float xFrom;
		public float yFrom;
		public float xTo;
		public float yTo;
		public float duration;
		
		public AnimationScrolling(Context context) {
			setDuration(500);
		}
		public AnimationScrolling host(LinearLayout3 value) {host = value; return this;}
		public AnimationScrolling xFrom(float value) {xFrom = value; return this;}
		public AnimationScrolling yFrom(float value) {yFrom = value; return this;}
		public AnimationScrolling xTo(float value) {xTo = value; return this;}
		public AnimationScrolling yTo(float value) {yTo = value; return this;}
		@Override public void applyTransformation(float ms, Transformation details) {
			if (host == null) return;
			
			/*float _xCrawl = (ms >= 1) ? 0 : ((xTo - xFrom) * (1 - ms));
			float _yCrawl = (ms >= 1) ? 0 : ((yTo - yFrom) * (1 - ms));*/
			float _xCrawl = (ms >= 1) ? 0 : (((xTo - xFrom) * 1.5f) * (1 - ms));
			float _yCrawl = (ms >= 1) ? 0 : (((yTo - yFrom) * 1.5f) * (1 - ms));
			if (host.width < host.widthInside) host.x += (int)_xCrawl;
			if (host.height < host.heightInside) host.y += (int)_yCrawl;
			
			if (ms >= 1) {
				if (host.x > 0) host.x(0);
				else if (host.widthInside <= host.width) {
					if (host.x < 0) host.x(0);
				}
				else if (host.x < 0 - (host.widthInside - host.width)) host.x(0 - (host.widthInside - host.width));
				
				if (host.y > 0) host.y(0);
				else if (host.heightInside <= host.height) {
					if (host.y < 0) host.y(0);
				}
				else if (host.y < 0 - (host.heightInside - host.height)) host.y(0 - (host.heightInside - host.height));
			}
			
			host.requestLayout();
		}
	}
	
	
	/* Global variables. */
	boolean _stateStarted = false;
	LinearLayout3 _overall = null;
	LinearLayout3 _boxToolbar = null;
	LinearLayout3 _boxFiles3 = null;
	LinearLayout3 _boxDocument3 = null;
	LinearLayout3 _boxPicture3 = null;
	LinearLayout3 _boxMusic = null;
	LinearLayout3 _boxWeb3 = null;
	LinearLayout3 _boxTest = null;
	SurfaceView2 _boxCamera = null;
	int _nGridsInALine = 30;
	int _widthSpan = 0;
	int _heightSpan = 0;
	int _widthMargin = 0;
	int _heightMargin =0;
	Dialog _dialog = null;
	LinearLayout3 _dialog1 = null;
	int _stateToDo = 0;
	ArrayList<String> _listFilesToDo = null;
	MediaPlayer _music = null;
	WebChromeClient _clientWebChrome = null;
	WebViewClient _clientWebGeneral = null;
	android.hardware.Camera _camera = null;
	String _valueFileContext = null;
	String _valueFilesHere = "/sdcard";
	String _valueDocumentHere = null;
	String _valuePictureHere = null;
	String _valueMusicHere = null;
	String _valueWebHere = "about:blank";
	DrawHelper _helperDraw = null;
	
	Packing _enginePackingLinear = new Packing() {public void action(View _this, boolean changed, int left, int top, int right, int bottom) {
		LinearLayout3 _this1 = ((LinearLayout3)_this).widthInside(0).heightInside(0);
		
		for (int _nth = 0; _nth < _this1.getChildCount(); _nth++) {
			View _child = _this1.getChildAt(_nth);
			int _widthChild = _child.getMeasuredWidth();
			int _heightChild = _child.getMeasuredHeight();
			
			if (_this1.getOrientation() == 1) {
				_child.layout(_this1.x, _this1.y + _this1.heightInside, _this1.x + _widthChild, _this1.y + _this1.heightInside + _heightChild);
				
				if (_this1.widthInside < _widthChild) _this1.widthInside(_widthChild);
				_this1.heightInside += _heightChild;
			}
			else {
				_child.layout(_this1.x + _this1.widthInside, _this1.y, _this1.x + _this1.widthInside + _widthChild, _this1.y + _heightChild);
				
				_this1.widthInside += _widthChild;
				if (_this1.heightInside < _heightChild) _this1.heightInside(_heightChild);
			}
		}
	}};
	TouchReaction _engineScroll = new TouchReaction() {public boolean action(View _this, MotionEvent motion) {
		LinearLayout3 _this1 = (LinearLayout3)_this;
		
		if (motion.getAction() == MotionEvent.ACTION_DOWN) {
			_this1.xDnDLast = 0;
			_this1.yDnDLast = 0;
			_this1.xSlideStart = (int)motion.getX();
			_this1.ySlideStart = (int)motion.getY();
		}
		else if (motion.getAction() == MotionEvent.ACTION_MOVE) {
			if (motion.getHistorySize() < 1) return true;
			
			int _xDnDNow = (int)(motion.getX() - motion.getHistoricalX(0));
			int _yDnDNow = (int)(motion.getY() - motion.getHistoricalY(0));
			_this1.xDnDLast = _xDnDNow;
			_this1.yDnDLast = _yDnDNow;
			
			if (_this1.width < _this1.widthInside) _this1.x(_this1.x + _xDnDNow);
			if (_this1.height < _this1.heightInside) _this1.y(_this1.y + _yDnDNow);
			_this1.requestLayout();
		}
		else if ((motion.getAction() == MotionEvent.ACTION_UP) || (motion.getAction() == MotionEvent.ACTION_CANCEL)) {
			AnimationScrolling _animation = new AnimationScrolling(getApplicationContext()).
				host(_this1).xFrom(_this1.x).yFrom(_this1.y).xTo(_this1.x + _this1.xDnDLast).yTo(_this1.y + _this1.yDnDLast);
			_this1.setAnimation(_animation);
			_this1.startAnimation(_animation);
		}
		
		return true;
	}};
	TouchReaction _engineTap = new TouchReaction() {public boolean action(View _this, MotionEvent motion) {
		LinearLayout3 _this1 = (LinearLayout3)_this;
		
		if (motion.getAction() == MotionEvent.ACTION_DOWN) {
			_this1.distance = 0;
		}
		else if (motion.getAction() == MotionEvent.ACTION_MOVE) {
			_this1.distance++;
			
			/* Moved lesser than 5px = Tap */
			if (_this1.distance >= 5) {
				return true;
			}
		}
		
		return false;
	}};
	
	
	/* Methods. */
	protected void onCreate(Bundle state) {
		/* Initialize app. */
		super.onCreate(state);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(new LinearLayout3(this).next(new Next() {public void action(View _this, int widthNatural, int heightNatural) {chapter2(_this, widthNatural, heightNatural);}}));
	}
	public void onBackPressed() {
		/*try {dismissDialog(0);} catch(Exception x) {}
					
		_dialog1.clear().label("ask-overall-quit").
			in(textLine(_overall.width - (_widthSpan * 2), "Quit?")).
			in(stick(3, "v-dialog", "Yes", null)).
			in(stick(3, "x-dialog", "No", null).index(10));
			
		showDialog(0);*/
		try {_camera.stopPreview(); _camera.release();} catch(Exception x) {}
		finish();
	}
	public Dialog onCreateDialog(int id) {return _dialog;}
	
	void chapter2(View _this, int widthNatural, int heightNatural) {
		/* (Real) Initialization. */
		if (_stateStarted == true) return;
		_stateStarted = true;
		_overall = ((LinearLayout3)_this).label("folder").width(normalizeNumber(widthNatural)).height(normalizeNumber(heightNatural)).direction(1).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				LinearLayout3 _this1 = (LinearLayout3)_this;
			}});
		_widthSpan = _overall.width / _nGridsInALine;
		_heightSpan = _overall.height / _nGridsInALine;
		_widthMargin = _overall.width % _nGridsInALine;
		_heightMargin = _overall.height % _nGridsInALine;
		_dialog = new Dialog(this);
		_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		_dialog1 = new LinearLayout3(this).direction(1).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				canvas.drawColor(0xff000000);
			}});
		_dialog.setContentView(_dialog1);
		_listFilesToDo = new ArrayList<String>();
		_music = new MediaPlayer();
		_helperDraw = new DrawHelper(_overall.width, _overall.height, _nGridsInALine);
		
		_boxCamera = new SurfaceView2(this).width(_overall.width).height(_overall.height);
			_boxCamera.setOnClickListener(new View.OnClickListener() {public void onClick(View _this) {
				SurfaceView2 _this1 = (SurfaceView2)_this;
				
				try {
					_camera.takePicture(
						new android.hardware.Camera.ShutterCallback() {public void onShutter() {}},
						new android.hardware.Camera.PictureCallback() {public void onPictureTaken(byte[] contents, android.hardware.Camera camera) {}},
						new android.hardware.Camera.PictureCallback() {public void onPictureTaken(byte[] contents, android.hardware.Camera camera) {
							try {
								Date _now = Calendar.getInstance().getTime();
								int _month0 = _now.getMonth();
								int _day0 = _now.getDate();
								int _hours0 = _now.getHours();
								int _minutes0 = _now.getMinutes();
								int _seconds0 = _now.getSeconds();
								String _year = Integer.toString(_now.getYear() + 1900);
								String _month = ((_month0 < 10) ? "0" : "") + Integer.toString(_month0);
								String _day = ((_day0 < 10) ? "0" : "") + Integer.toString(_day0);
								String _hours = ((_hours0 < 10) ? "0" : "") + Integer.toString(_hours0);
								String _minutes = ((_minutes0 < 10) ? "0" : "") + Integer.toString(_minutes0);
								String _seconds = ((_seconds0 < 10) ? "0" : "") + Integer.toString(_seconds0);
								String _path = "/sdcard/photo_" + _year + _month + _day + "_" + _hours + _minutes + _seconds + ".png";
								
								Bitmap _contents0 = BitmapFactory.decodeByteArray(contents, 0, contents.length);
								Matrix _matrix = new Matrix();
								_matrix.postRotate(90);
								
								FileOutputStream _stream = new FileOutputStream(_path);
								Bitmap.createBitmap(_contents0, 0, 0, _contents0.getWidth(), _contents0.getHeight(), _matrix, true).compress(Bitmap.CompressFormat.PNG, 100, _stream);
								_stream.close();
								
								Toast.makeText(getApplicationContext(), "Photo saved as: " + _path, 1).show();
								
								_camera.startPreview();
							}
							catch(Exception x) {_camera.startPreview();}
						}}
					);
				}
				catch(Exception x) {x.printStackTrace(); _camera.startPreview();}
			}});
		_overall.in(_boxCamera);
		
		Toast.makeText(this, "Tap the screen to take photo, and activate back key to quit.\nPhotos will be saved as /sdcard/photo_(date)_(time).png.", 1).show();
		
		_camera = android.hardware.Camera.open(0);
		_camera.setDisplayOrientation(90);
		_boxCamera.getHolder().addCallback(new SurfaceHolder.Callback() {
			public void surfaceCreated(SurfaceHolder holder) {
				try {_camera.setPreviewDisplay(holder);} catch(Exception x) {}
			}
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
			public void surfaceDestroyed(SurfaceHolder holder) {}
		});
		_camera.startPreview();
	}
	
	void zoneDo(String command, String argument) {
		Log.e("Creole", "ZONE_DO " + command + " " + argument);
		
		if (command == null) return;
		if (argument == null) return;
		
		if (command.compareTo("sorry") == 0) {
			Toast.makeText(this, "Sorry nothing", 1).show();
		}
		else if (command.compareTo("show") == 0) {
			try {_overall.out(1);} catch(Exception x) {}
			
			if (argument.compareTo("files") == 0) _overall.in(_boxFiles3).label("folder");
			else if (argument.compareTo("document") == 0) _overall.in(_boxDocument3).label("paper");
			else if (argument.compareTo("picture") == 0) _overall.in(_boxPicture3).label("mountain");
			else if (argument.compareTo("music") == 0) _overall.in(_boxMusic).label("repeat");
			else if (argument.compareTo("web") == 0) _overall.in(_boxWeb3).label("globe");
			else if (argument.compareTo("test") == 0) _overall.in(_boxTest).label("test");
			
			_boxToolbar.invalidate();
		}
		else if (command.compareTo("load") == 0) {
			if (argument == null) return;
			
			String _here = _overall.label;
			if (_here.compareTo("folder") == 0) {
				try {
					File[] _listChildren = new File(argument).listFiles();
					if (_listChildren == null) {trySomethingAboutFile(argument); return;}
					ArrayList<File> _listChildren1 = new ArrayList<File>(Arrays.asList(_listChildren));
					_listChildren1.sort(new CompareFiles());
					
					ArrayList<File> _listChildrenDirectory = new ArrayList<File>();
					ArrayList<File> _listChildrenFile = new ArrayList<File>();
					for (File _child : _listChildren1.toArray(_listChildren)) {
						try {
							if (_child.isDirectory() == true) _listChildrenDirectory.add(_child);
							else _listChildrenFile.add(_child);
						}
						catch(Exception x) {_listChildrenFile.add(_child);}
					}
					
					_boxFiles3.clear().x(0).y(0);
					for (Object _child : _listChildrenDirectory.toArray()) _boxFiles3.in(fileitem2(((File)_child).getPath()));
					for (Object _child : _listChildrenFile.toArray()) _boxFiles3.in(fileitem2(((File)_child).getPath()));
					_valueFilesHere = argument;
					_boxToolbar.invalidate();
				}
				catch(Exception x) {x.printStackTrace();}
			}
			else if (_here.compareTo("paper") == 0) {
				try {
					FileInputStream _stream = new FileInputStream(argument);
					byte[] _contents = new byte[(int)new File(argument).length()];
					_stream.read(_contents);
					_stream.close();
					
					((EditText2)_boxDocument3.getChildAt(0)).setText(new String(_contents));
					
					_valueDocumentHere = argument;
					_boxToolbar.invalidate();
				}
				catch(Exception x) {x.printStackTrace();}
			}
			else if (_here.compareTo("mountain") == 0) {
				try {
					final Bitmap _picture = BitmapFactory.decodeFile(argument);
					final int _widthPicture = _picture.getWidth();
					final int _heightPicture = _picture.getHeight();
					_boxPicture3.clear().x(0).y(0).
						width((_overall.width < _widthPicture) ? _overall.width : _widthPicture).
						height((_overall.height < _heightPicture) ? _overall.height : _heightPicture).
						looks(new Looks() {public void action(View _this, Canvas canvas) {
							canvas.drawBitmap(_picture, ((LinearLayout3)_this).x, ((LinearLayout3)_this).y, new Paint());
						}}).
						in(new LinearLayout3(this).fixedSize(true).width(_widthPicture).height(_heightPicture));
					
					_valuePictureHere = argument;
					_boxToolbar.invalidate();
				}
				catch(Exception x) {x.printStackTrace();}
			}
			else if (_here.compareTo("repeat") == 0) {
				try {_music.reset();} catch(Exception x) {}
				try {_music.setDataSource(argument);} catch(Exception x) {}
				try {_music.prepare();} catch(Exception x) {}
				try {
					_music.start();
					
					_valueMusicHere = argument;
					_boxToolbar.invalidate();
					_boxMusic.getChildAt(0).invalidate();
					_boxMusic.getChildAt(1).invalidate();
					_boxMusic.getChildAt(2).invalidate();
				} catch(Exception x) {}
			}
			else if (_here.compareTo("globe") == 0) {
				try {
					((WebView2)_boxWeb3.getChildAt(0)).loadUrl(argument);
					
					_valueWebHere = argument;
					_boxToolbar.invalidate();
				} catch(Exception x) {}
			}
			else if (_here.compareTo("test") == 0) zoneDo("sorry", "sorry");
		}
	}
	
	int normalizeNumber(int number) {
		int _number = number;
		if (_number < 0) _number = 0 - _number;
		
		if (_number < 0x10000000) return _number;
		else if (_number < 0x20000000) return _number - 0x10000000;
		else if (_number < 0x30000000) return _number - 0x20000000;
		else if (_number < 0x40000000) return _number - 0x30000000;
		else if (_number < 0x50000000) return _number - 0x40000000;
		else if (_number < 0x60000000) return _number - 0x50000000;
		else if (_number < 0x70000000) return _number - 0x60000000;
		else if (_number < 0x80000000) return _number - 0x70000000;
		else if (_number < 0x90000000) return _number - 0x80000000;
		else return _number - 0x90000000;
	}
	void loadTest(String path) {
		File _file = new File(path);
		try {
			File[] _listChildren = _file.listFiles();
			if (_listChildren == null) {trySomethingAboutFile(path); return;}
			ArrayList<File> _listChildren1 = new ArrayList<File>(Arrays.asList(_listChildren));
			_listChildren1.sort(new CompareFiles());
			
			ArrayList<File> _listChildrenDirectory = new ArrayList<File>();
			ArrayList<File> _listChildrenFile = new ArrayList<File>();
			for (File _child : _listChildren1.toArray(_listChildren)) {
				try {
					if (_child.isDirectory() == true) _listChildrenDirectory.add(_child);
					else _listChildrenFile.add(_child);
				}
				catch(Exception x) {_listChildrenFile.add(_child);}
			}
			
			_boxTest.clear().x(0).y(0).requestLayout();
			for (Object _child : _listChildrenDirectory.toArray()) {
				_boxTest.in(stick(2, "whatever", ((File)_child).getPath(), "menu2"));
			}
			for (Object _child : _listChildrenFile.toArray()) {
				_boxTest.in(stick(2, "whatever", ((File)_child).getPath(), "menu2"));
			}
			_boxTest.requestLayout();
			_boxTest.invalidate();
		}
		catch(Exception x) {x.printStackTrace();}
	}
	void clearWeb() {
		try {
			WebView2 _web = (WebView2)_boxWeb3.getChildAt(0);
			_web.loadUrl("about:blank");
			_web.clearCache(true);
			_web.clearClientCertPreferences(null);
			_web.clearFormData();
			_web.clearHistory();
			_web.clearMatches();
			_web.clearSslPreferences();
			
			_valueWebHere = "about:blank";
			_boxToolbar.invalidate();
		} catch(Exception x) {}
	}
	void trySomethingAboutFile(String path) {
		String _mime;
		try {_mime = new URL("file://" + path).openConnection().getContentType();}
		catch(Exception e) {_mime = "application/octet-stream";}
		
		Uri _path = null;
		Cursor _hereID = getContentResolver().query(
			MediaStore.Files.getContentUri("external"),
			new String[]{BaseColumns._ID},
			MediaStore.MediaColumns.DATA + " LIKE '%" + new File(path).getName() + "%'",
			null,
			null
		);
		Cursor _herePath = getContentResolver().query(
			MediaStore.Files.getContentUri("external"),
			new String[]{MediaStore.MediaColumns.DATA},
			MediaStore.MediaColumns.DATA + " LIKE '%" + new File(path).getName() + "%'",
			null,
			null
		);
		if ((_hereID != null) && (_herePath != null)) {
			int _nPathes = _hereID.getCount();
			for (int _nth = 0; _nth < _nPathes; _nth++) {
				_hereID.moveToPosition(_nth);
				_herePath.moveToPosition(_nth);
				String _path1 = path;
				if (_path1.startsWith("/sdcard") == true) _path1 = _path1.replaceFirst("/sdcard", "/storage/emulated/0");
				if (_path1.compareTo(_herePath.getString(_nth)) == 0) {
					_path = Uri.parse(MediaStore.Files.getContentUri("external") + "/" + _hereID.getString(_nth));
					break;
				}
			}
		}
		
		try {
			Intent _worker = new Intent();
			_worker.setDataAndType(_path, _mime);
			_worker.setAction(Intent.ACTION_VIEW);
			startActivity(_worker);
		}
		catch(Exception x) {x.printStackTrace();}
	}
	boolean fileCopy(String from, String to) {
		if ((from == null) || (to == null)) return false;
		
		try {
			File _from = new File(from);
			if (_from.isDirectory() == true) {
				new File(to).mkdirs();
				
				File[] _children = _from.listFiles();
				if (_children != null) {
					for (File _child : _children) {
						String _nameChild = _child.getName();
						if (fileCopy(_child.getPath(), to + "/" + _nameChild) != true) break; 
					}
				}
			}
			else {
				new File(to).createNewFile();
				
				FileInputStream _streamFrom = new FileInputStream(from);
				FileOutputStream _streamTo = new FileOutputStream(from);
				int _z = 0;
				while (_z == 0) {
					int _byte = _streamFrom.read();
					if (_byte == -1) break;
					_streamTo.write(_byte);
				}
				_streamTo.close();
				_streamFrom.close();
			}
		}
		catch(Exception x) {return false;}
		
		return true;
	}
	boolean fileDelete(String path) {
		if (path == null) return false;
		
		try {
			File _path = new File(path);
			if (_path.isDirectory() == true) {
				File[] _children = _path.listFiles();
				if (_children != null) {
					for (File _child : _children) {
						if (fileDelete(_child.getPath()) != true) break;
					}
				}
			}
			
			if (_path.delete() != true) return false;
		}
		catch(Exception x) {return false;}
		
		return true;
	}
	boolean fileMove(String from, String to) {
		if ((from == null) || (to == null)) return false;
		
		try {
			File _from = new File(from);
			if (_from.renameTo(new File(to)) != true) {
				if (fileCopy(from, to) != true) return false;
				if (fileDelete(from) != true) return false;
			}
		}
		catch(Exception x) {return false;}
		
		return true;
	}
	byte[] documentEncrypt(byte[] document, byte[] passphrase, byte[] seed) {
		if (document == null) return null;
		if (passphrase == null) return document;
		
		Cipher _cipher = null;
		try {
			/*Cipher _cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			_cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(passphrase, "AES"));*/
			_cipher = Cipher.getInstance("AES_256/CBC/PKCS5Padding");
			_cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(passphrase, "AES_256/CBC/PKCS5Padding"), new IvParameterSpec(seed));
			
			return _cipher.doFinal(document);
		}
		catch(Exception x) {x.printStackTrace(); return null;}
	}
	byte[] documentDecrypt(byte[] document, byte[] passphrase, byte[] seed) {
		if (document == null) return null;
		if (passphrase == null) return document;
		
		Cipher _cipher = null;
		try {
			/*Cipher _cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			_cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(passphrase, "AES"));*/
			_cipher = Cipher.getInstance("AES_256/CBC/PKCS5Padding");
			_cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(passphrase, "AES_256/CBC/PKCS5Padding"), new IvParameterSpec(seed));
			
			return _cipher.doFinal(document);
		}
		catch(Exception x) {x.printStackTrace(); return null;}
	}
	String musicProfile(String path, String category) {
		MediaMetadataRetriever _tree = new MediaMetadataRetriever();
		try {
			_tree.setDataSource(path);
			if (category.compareTo("title") == 0) return _tree.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
			else if (category.compareTo("artist") == 0) return _tree.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
			else if (category.compareTo("album") == 0) return _tree.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
			else return null;
		}
		catch(Exception x) {return null;}
	}
	private TextView2 buttonIcon(int type, String value) {
		return new TextView2(this).index(type).label(value).width((type == 200) ? (_overall.width - (_heightSpan * 4)) : _heightSpan * 2).height(_heightSpan * 2).
			whenClicked(new View.OnClickListener() {public void onClick(View _this) {
				TextView2 _this1 = (TextView2)_this;
				String _label = _this1.label;
				LinearLayout3 _parent = (LinearLayout3)_this.getParent();
				String _id = _parent.label;
				
				if ((_this1.index == 100) || (_this1.index == 200)) {
					if (_label.compareTo("zoneselect") == 0) {
						try {dismissDialog(0);} catch(Exception x) {}
						
						_dialog1.clear().label("menu-zoneselect").
							in(menuitem2("zone-files")).
							in(menuitem2("zone-document")).
							in(menuitem2("zone-picture")).
							in(menuitem2("zone-music")).
							in(menuitem2("zone-web")).
							in(menuitem2("zone-test")).
							in(menuitem2("close"));
							
						showDialog(0);
					}
					else if (_label.compareTo("address") == 0) {
						try {dismissDialog(0);} catch(Exception x) {}
								
						if (_overall.label.compareTo("folder") == 0) {
							_dialog1.clear().label("ask-files-path").
								in(entryGeneral(_overall.width - (_heightSpan * 2), _valueFilesHere)).
								in(stick(3, "v-dialog", "Go", null)).
								in(stick(3, "turnup", "Parent", null)).
								in(stick(3, "x-dialog", "Cancel", null).index(10));
						}
						else if (_overall.label.compareTo("paper") == 0) {
							_dialog1.clear().label("ask-document-path").
								in(entryGeneral(_overall.width - (_heightSpan * 2), (_valueDocumentHere != null) ? _valueDocumentHere : "")).
								in(stick(3, "v-dialog", "Load", null)).
								in(stick(3, "x-dialog", "Cancel", null).index(10));
						}
						else if (_overall.label.compareTo("mountain") == 0) {
							_dialog1.clear().label("ask-picture-path").
								in(entryGeneral(_overall.width - (_heightSpan * 2), (_valuePictureHere != null) ? _valuePictureHere : "")).
								in(stick(3, "v-dialog", "Load", null)).
								in(stick(3, "x-dialog", "Cancel", null).index(10));
						}
						else if (_overall.label.compareTo("repeat") == 0) {
							_dialog1.clear().label("ask-music-path").
								in(entryGeneral(_overall.width - (_heightSpan * 2), (_valuePictureHere != null) ? _valuePictureHere : "")).
								in(stick(3, "v-dialog", "Load", null)).
								in(stick(3, "x-dialog", "Cancel", null).index(10));
						}
						else if (_overall.label.compareTo("globe") == 0) {
							_dialog1.clear().label("ask-web-path").
								in(entryGeneral(_overall.width - (_heightSpan * 2), (_valuePictureHere != null) ? _valuePictureHere : "")).
								in(stick(3, "v-dialog", "Load", null)).
								in(stick(3, "x-dialog", "Cancel", null).index(10));
						}
						
						showDialog(0);
					}
					else if (_this1.label.compareTo("zonemenu") == 0) {
						try {dismissDialog(0);} catch(Exception x) {}
					
						if (_overall.label.compareTo("folder") == 0) {
							_dialog1.clear().label("menu-files-default").
								in(menuitem2("files-new")).
								in(menuitem2("files-selectall")).
								in(menuitem2("files-deselectall")).
								in(menuitem2("files-reverseselection")).
								in(menuitem2("files-copy")).
								in(menuitem2("files-cut")).
								in(menuitem2("files-paste")).
								in(menuitem2("files-delete"));
						}
						else if (_overall.label.compareTo("paper") == 0) {
							_dialog1.clear().label("menu-document-default").
								in(menuitem2("document-new")).
								in(menuitem2("document-decrypt")).
								in(menuitem2("document-save")).
								in(menuitem2("document-encrypt"));
						}
						else if (_overall.label.compareTo("mountain") == 0) {
							_dialog1.clear().label("menu-mountain-default").
								in(menuitem2("picture-wallpaper"));
						}
						else if (_overall.label.compareTo("repeat") == 0) {
							_dialog1.clear().label("menu-music-default").
								in(menuitem2("music-playpause")).
								in(menuitem2("music-repeat"));
						}
						else if (_overall.label.compareTo("globe") == 0) {
							_dialog1.clear().label("menu-web-default").
								in(menuitem2("web-clear"));
						}
						/*else if (_overall.label.compareTo("test") == 0) {
							_dialog1.clear().label("menu-web-default").
								in(menuitem2("web-clear"));
						}*/
						_dialog1.in(menuitem2("close"));
						
						showDialog(0);
					}
					else if (_label.compareTo("menuitem") == 0) {
						/* Close the dialog */
						if (_id.compareTo("close") == 0) dismissDialog(0);
						
						/* Zones */
						else if (_id.startsWith("zone") == true) {
							if (_id.compareTo("zone-files") == 0) zoneDo("show", "files");
							else if (_id.compareTo("zone-document") == 0) zoneDo("show", "document");
							else if (_id.compareTo("zone-picture") == 0) zoneDo("show", "picture");
							else if (_id.compareTo("zone-music") == 0) zoneDo("show", "music");
							else if (_id.compareTo("zone-web") == 0) zoneDo("show", "web");
							else if (_id.compareTo("zone-test") == 0) zoneDo("show", "test");
							
							dismissDialog(0);
						}
						
						/* File manager */
						else if (_id.startsWith("files") == true) {
							if (_id.endsWith("new") == true) {
								dismissDialog(0);
						
								_dialog1.clear().label("ask-files-create").
									in(entryGeneral(_overall.width - (_heightSpan * 2), _valueFilesHere + "/new")).
									in(stick(3, "paper-dialog", "File", null)).
									in(stick(3, "folder-dialog", "Directory", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
									
								showDialog(0);
							}
							else if (_id.endsWith("selectall") == true) {
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									_boxFiles3.child(_nth).index(1).invalidate();
								}
								
								dismissDialog(0);
							}
							else if (_id.endsWith("deselectall") == true) {
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									_boxFiles3.child(_nth).index(0).invalidate();
								}
								
								dismissDialog(0);
							}
							else if (_id.endsWith("reverseselection") == true) {
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									LinearLayout3 _item = _boxFiles3.child(_nth);
									_item.index((_item.index == 0) ? 1 : 0).invalidate();
								}
								
								dismissDialog(0);
							}
							else if (_id.endsWith("copy") == true) {
								_stateToDo = 1;
								_listFilesToDo.clear();
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									LinearLayout3 _item = _boxFiles3.child(_nth);
									if (_item.index == 1) _listFilesToDo.add(_item.label);
								}
								
								dismissDialog(0);
							}
							else if (_id.endsWith("cut") == true) {
								_stateToDo = 2;
								_listFilesToDo.clear();
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									LinearLayout3 _item = _boxFiles3.child(_nth);
									if (_item.index == 1) _listFilesToDo.add(_item.label);
								}
								
								dismissDialog(0);
							}
							else if (_id.endsWith("paste") == true) {
								dismissDialog(0);
								
								if ((_stateToDo != 1) && (_stateToDo != 2)) return;
								_dialog1.clear().label("ask-files-paste").
									in(textLine(_overall.width - (_widthSpan * 2), ((_stateToDo == 1) ? "Copy " : "Move ") + Integer.toString(_listFilesToDo.size()) + " file(s) here?")).
									in(stick(3, "v-dialog", "Yes", null)).
									in(stick(3, "x-dialog", "No", null).index(10));
									
								showDialog(0);
							}
							else if (_id.endsWith("delete") == true) {
								int _nFilesSelected = 0;
								for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
									LinearLayout3 _item = _boxFiles3.child(_nth);
									if (_item.index == 1) _nFilesSelected++;
								}
								if (_nFilesSelected < 1) return;
								
								dismissDialog(0);
								
								_dialog1.clear().label("ask-files-delete").
									in(textLine(_overall.width - (_widthSpan * 2), "Delete " + Integer.toString(_nFilesSelected) + " selected file(s)?")).
									in(stick(3, "v-dialog", "Yes", null)).
									in(stick(3, "x-dialog", "No", null).index(10));
									
								showDialog(0);
							}
						}
						
						/* File manager (Context menu) */
						else if (_id.startsWith("context") == true) {
							if (_id.endsWith("internal") == true) {
								String _mime;
								try {_mime = new URL("file://" + _valueFileContext).openConnection().getContentType();}
								catch(Exception e) {_mime = "application/octet-stream";}
								String _type = _mime.split("/")[0];
								
								_overall.out(1);
								if (_type.compareTo("image") == 0) {
									_overall.in(_boxPicture3).label("mountain");
									zoneDo("load", _valueFileContext);
								}
								else if ((_type.compareTo("audio") == 0) || (_type.compareTo("video") == 0)) {
									_overall.in(_boxMusic).label("repeat");
									zoneDo("load", _valueFileContext);
								}
								else {
									_overall.in(_boxDocument3).label("paper");
									zoneDo("load", _valueFileContext);
								}
								_boxToolbar.invalidate();
								
								dismissDialog(0);
							}
							else if (_id.endsWith("copy") == true) {
								dismissDialog(0);
									
								_dialog1.clear().label("ask-files-copy1").
									in(entryGeneral(_overall.width - (_heightSpan * 2), _valueFileContext + "-new")).
									in(stick(3, "v-dialog", "Copy", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
								
								showDialog(0);
							}
							else if (_id.endsWith("move") == true) {
								dismissDialog(0);
										
								_dialog1.clear().label("ask-files-move1").
									in(entryGeneral(_overall.width - (_heightSpan * 2), _valueFileContext + "-new")).
									in(stick(3, "v-dialog", "Move / Rename", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
								
								showDialog(0);
							}
							else if (_id.endsWith("delete") == true) {
								dismissDialog(0);
								
								_dialog1.clear().label("ask-files-delete1").
									in(textLine(_overall.width - (_widthSpan * 2), "Delete this file?")).
									in(stick(3, "v-dialog", "Yes", null)).
									in(stick(3, "x-dialog", "No", null).index(10));
								
								showDialog(0);
							}
						}
						
						/* Text editor */
						else if (_id.startsWith("document") == true) {
							if (_id.endsWith("new") == true) {
								dismissDialog(0);
								
								_dialog1.clear().label("ask-document-new").
									in(textLine(_overall.width - (_widthSpan * 2), "Unsaved data will be lost.")).
									in(stick(3, "v-dialog", "OK", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
									
								showDialog(0);
							}
							else if (_id.endsWith("decrypt") == true) {
								dismissDialog(0);
								
								_dialog1.clear().label("ask-document-load-encrypted").
									in(entryGeneral(_overall.width - (_heightSpan * 2), null)).
									in(entryMasked(_overall.width - (_heightSpan * 2), "0000")).
									in(entryGeneral(_overall.width - (_heightSpan * 2), "")).
									in(stick(3, "v-dialog", "Load", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
									
								showDialog(0);
							}
							else if (_id.endsWith("save") == true) {
								dismissDialog(0);
									
								_dialog1.clear().label("ask-document-save").
									in(entryGeneral(_overall.width - (_heightSpan * 2), (_valueDocumentHere != null) ? _valueDocumentHere : "/sdcard/new")).
									in(stick(3, "v-dialog", "Save", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
									
								showDialog(0);
							}
							else if (_id.endsWith("encrypt") == true) {
								dismissDialog(0);
									
								_dialog1.clear().label("ask-document-save-encrypted").
									in(entryGeneral(_overall.width - (_heightSpan * 2), (_valueDocumentHere != null) ? _valueDocumentHere : "/sdcard/new")).
									in(entryMasked(_overall.width - (_heightSpan * 2), "0000")).
									in(entryGeneral(_overall.width - (_heightSpan * 2), "")).
									in(stick(3, "v-dialog", "Save", null)).
									in(stick(3, "x-dialog", "Cancel", null).index(10));
									
								showDialog(0);
							}
						}
						
						/* Image viewer */
						else if (_id.startsWith("picture") == true) {
							if (_id.endsWith("wallpaper") == true) {
								try {
									((WallpaperManager)getSystemService(Context.WALLPAPER_SERVICE)).setBitmap(BitmapFactory.decodeFile(_valuePictureHere));
									
									dismissDialog(0);
								}
								catch(Exception x) {x.printStackTrace();}
							}
						}
						
						/* Music player */
						else if (_id.startsWith("music") == true) {
							if (_id.endsWith("playpause") == true) {
								try {
									if (_music.isPlaying() == true) _music.pause();
									else _music.start();
									
									dismissDialog(0);
								}
								catch(Exception x) {Toast.makeText(getApplicationContext(), "No loaded music", 1).show();}
							}
							else if (_id.endsWith("repeat") == true) {
								_music.setLooping((_music.isLooping() == true) ? false : true);
									
								((LinearLayout3)_this1.getParent()).requestLayout();
								((LinearLayout3)_this1.getParent()).invalidate();
							}
						}
						
						/* Web browser */
						else if (_id.startsWith("web") == true) {
							if (_id.endsWith("clear") == true) {
								clearWeb();
								
								dismissDialog(0);
							}
						}
						
						/* Test zone */
						/*else if (_id.startsWith("test") == true) {
							if (_id.endsWith("clear") == true) {
								clearWeb();
								
								dismissDialog(0);
							}
						}*/
					}
					else if (_this1.label.compareTo("fileselect") == 0) {
						_parent.index((_parent.index == 0) ? 1 : 0);
						_parent.invalidate();
					}
					else if (_this1.label.compareTo("filerun") == 0) {
						zoneDo("load", _id);
					}
					else if (_this1.label.compareTo("contextmenu") == 0) {
						_valueFileContext = _id;
						try {dismissDialog(0);} catch(Exception x) {}
						
						_dialog1.clear().label("menu-files-context").
							in(textLine(_overall.width - (_widthSpan * 2), new File(_id).getName())).
							in(menuitem2("context-internal")).
							in(menuitem2("context-copy")).
							in(menuitem2("context-move")).
							in(menuitem2("context-delete")).
							in(menuitem2("close"));
							
						showDialog(0);
					}
				}
			}});
	}
	private EditText2 entryGeneral(int width, String valueDefault) {
		EditText2 _entry = new EditText2(this).width(width).height(_heightSpan * 2).text(valueDefault).oneline(true).
				looks(new Looks() {public void action(View _this, Canvas canvas) {
					canvas.drawColor(0xffbfff00);
				}});
			_entry.setTextSize(TypedValue.COMPLEX_UNIT_PX, _heightSpan);
			_entry.setTextColor(0xff000000);
			_entry.setPadding(_heightSpan / 5, 0, _heightSpan / 5, 0);
			
		return _entry;
	}
	private EditText2 entryMasked(int width, String valueDefault) {
		EditText2 _entry = new EditText2(this).width(width).height(_heightSpan * 2).text(valueDefault).oneline(true).
				looks(new Looks() {public void action(View _this, Canvas canvas) {
					canvas.drawColor(0xffbfff00);
				}});
			_entry.setTextSize(TypedValue.COMPLEX_UNIT_PX, _heightSpan);
			_entry.setTextColor(0xff000000);
			_entry.setPadding(_heightSpan / 5, 0, _heightSpan / 5, 0);
			_entry.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
			
		return _entry;
	}
	private TextView2 textLine(int width, String text) {
		TextView2 _text = new TextView2(this).width(_overall.width - (_heightSpan * 4)).height(_heightSpan * 2).text(text).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				TextView2 _this1 = (TextView2)_this;
				Paint _drawer = new Paint();
				_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
				_drawer.setColor(0xffffffff);
				_drawer.setTextSize(_heightSpan);
				canvas.drawText(_this1.getText().toString(), _heightSpan / 5, _heightSpan * 1.375f, _drawer);
			}});
			
		return _text;
	}
	
	private LinearLayout3 stick(int type, String icon1, String label, String icon2) {
		/*
			0 = Normal (default)
			1 = Top toolbar
			2 = File item
			3 = Menu item
		*/
		
		/* Overall */
		LinearLayout3 _stick = new LinearLayout3(this).width(_overall.width).height(_heightSpan * 2).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				LinearLayout3 _this1 = (LinearLayout3)_this;
				
				if (_this1.index == 10) canvas.drawColor(0xffff3f3f);
				else if (_this1.index == 1) canvas.drawColor(0xff003f00);
			}});
			if (type == 2) _stick.label(label);
			
			/* Icon (left) */
			if (icon1 != null) {
				if (type == 1) _stick.in(buttonIcon(0, icon1));
				else if (type == 2) {
					try {_stick.in(buttonIcon(10, (new File(label).listFiles() != null) ? "folder-file" : "paper-file"));}
					catch(Exception x) {_stick.in(buttonIcon(10, "paper-file"));}
				}
				else _stick.in(buttonIcon(10, icon1));
			}
			
			/* Label */
			if (type == 3) {
				_stick.in(
					new TextView2(this).label(label).width(_overall.width - (_heightSpan * 2)).height(_heightSpan * 2).
						looks(new Looks() {public void action(View _this, Canvas canvas) {
							TextView2 _this1 = (TextView2)_this;
							Paint _drawer = new Paint();
							_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
							_drawer.setColor(0xffffffff);
							_drawer.setTextSize(_heightSpan);
							canvas.drawText(_this1.label, _heightSpan / 5, _heightSpan * 1.375f, _drawer);
						}}).
						whenClicked(new View.OnClickListener() {public void onClick(View _this) {
							TextView2 _this1 = (TextView2)_this;
							boolean _hasNext = false;
							
							if (_dialog1.label.compareTo("ask-overall-quit") == 0) {
								if (_this1.label.compareTo("Yes") == 0) {
									try {_music.stop();} catch(Exception x) {}
									try {_music.release();} catch(Exception x) {}
									try {clearWeb();} catch(Exception x) {}
									
									finish();
								}
								else dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-path") == 0) {
								if (_this1.label.compareTo("Go") == 0) {
									try {zoneDo("load", ((EditText2)_dialog1.getChildAt(0)).getText().toString());} catch(Exception x) {return;}
								}
								else if (_this1.label.compareTo("Parent") == 0) {
									try {zoneDo("load", new File(_valueFilesHere).getParent());} catch(Exception x) {}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-create") == 0) {
								String _answer = null;
								try {_answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();} catch(Exception x) {return;}
								
								if (_answer != null) {
									File _file = new File(_answer);
									try {
										if (_this1.label.compareTo("File") == 0) _file.createNewFile();
										else if (_this1.label.compareTo("Directory") == 0) _file.mkdirs();
										
										zoneDo("load", _valueFilesHere);
									}
									catch(Exception x) {}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-paste") == 0) {
								if (_this1.label.compareTo("Yes") == 0) {
									for (int _nth = 0; _nth < _listFilesToDo.size(); _nth++) {
										String _from = _listFilesToDo.get(_nth);
										String _name = new File(_from).getName();
										String _to = _valueFilesHere + "/" + _name;
										
										boolean _result = (_stateToDo == 2) ? fileMove(_from, _to) : fileCopy(_from, _to);
										if (_result != true) break;
									}
									
									zoneDo("load", _valueFilesHere);
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-delete") == 0) {
								if (_this1.label.compareTo("Yes") == 0) {
									for (int _nth = 0; _nth < _boxFiles3.getChildCount(); _nth++) {
										LinearLayout3 _item = _boxFiles3.child(_nth);
										if (_item.index == 1) {
											if (fileDelete(_item.label) != true) break;
										}
									}
									
									zoneDo("load", _valueFilesHere);
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-copy1") == 0) {
								if (_this1.label.compareTo("Copy") == 0) {
									String _to = ((EditText2)_dialog1.getChildAt(0)).getText().toString();
										
									if (fileCopy(_valueFileContext, _to) == true) {
										zoneDo("load", _valueFilesHere);
										Toast.makeText(getApplicationContext(), "Successfully copied", 1).show();
									}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-move1") == 0) {
								if (_this1.label.compareTo("Move / Rename") == 0) {
									String _to = ((EditText2)_dialog1.getChildAt(0)).getText().toString();
										
									if (fileMove(_valueFileContext, _to) == true) {
										zoneDo("load", _valueFilesHere);
										Toast.makeText(getApplicationContext(), "Successfully moved / renamed", 1).show();
									}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-files-delete1") == 0) {
								if (_this1.label.compareTo("Yes") == 0) {
									if (fileDelete(_valueFileContext) == true) {
										zoneDo("load", _valueFilesHere);
										Toast.makeText(getApplicationContext(), "Successfully deleted", 1).show();
									}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-document-path") == 0) {
								if (_this1.label.compareTo("Load") == 0) {
									try {zoneDo("load", ((EditText2)_dialog1.getChildAt(0)).getText().toString());} catch(Exception x) {return;}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-document-new") == 0) {
								if (_this1.label.compareTo("OK") == 0) {
									EditText2 _editor = (EditText2)_boxDocument3.getChildAt(0);
									_editor.setSelection(0, 0);
									_editor.setText("");
									_valueDocumentHere = null;
									_boxToolbar.invalidate();
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-document-load-encrypted") == 0) {
								if (_this1.label.compareTo("Load") == 0) {
									String _passphrase = ((EditText2)_dialog1.getChildAt(1)).getText().toString();
									if (_passphrase.length() != 32) {
										if (_passphrase.length() < 32) {
											while (_passphrase.length() < 32) _passphrase += "0";
										}
										else if (_passphrase.length() > 32) {
											Toast.makeText(getApplicationContext(), "Passphrase length must not exceed 32 bytes", 1).show();
											return;
										}
									}
									
									byte[] _seed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
									try {
										String _pathSeed = ((EditText2)_dialog1.getChildAt(2)).getText().toString();
										if (new File(_pathSeed).length() >= 16) {
											FileInputStream _stream = new FileInputStream(_pathSeed);
											_stream.read(_seed, 0, 16);
											_stream.close();
										}
									}
									catch(Exception x) {}
									
									String _answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();
									if (_answer.startsWith("/") != true) _answer = "/sdcard/" + _answer;
									try {
										FileInputStream _stream = new FileInputStream(_answer);
										byte[] _contentsEncrypted = new byte[(int)new File(_answer).length() - 7];
										_stream.skip(7);
										_stream.read(_contentsEncrypted);
										_stream.close();
										
										((EditText2)_boxDocument3.getChildAt(0)).setText(new String(documentDecrypt(_contentsEncrypted, _passphrase.getBytes("UTF-8"), _seed)));
										
										_valueDocumentHere = _answer;
										_boxToolbar.invalidate();
									}
									catch(Exception x) {x.printStackTrace(); return;}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-document-save") == 0) {
								if (_this1.label.compareTo("Save") == 0) {
									try {
										String _answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();
										if (_answer.startsWith("/") != true) _answer = "/sdcard/" + _answer;
										
										FileOutputStream _stream = new FileOutputStream(_answer);
										_stream.write(((EditText2)_boxDocument3.getChildAt(0)).getText().toString().getBytes("UTF-8"));
										_stream.close();
										
										Toast.makeText(getApplicationContext(), "Saved as: " + _answer, 1).show();
										_valueDocumentHere = _answer;
										_boxToolbar.invalidate();
									}
									catch(Exception x) {return;}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-document-save-encrypted") == 0) {
								if (_this1.label.compareTo("Save") == 0) {
									String _passphrase = ((EditText2)_dialog1.getChildAt(1)).getText().toString();
									if (_passphrase.length() != 32) {
										if (_passphrase.length() < 32) {
											while (_passphrase.length() < 32) _passphrase += "0";
										}
										else if (_passphrase.length() > 32) {
											Toast.makeText(getApplicationContext(), "Passphrase length must not exceed 32 bytes", 1).show();
											return;
										}
									}
									
									byte[] _seed = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
									try {
										String _pathSeed = ((EditText2)_dialog1.getChildAt(2)).getText().toString();
										if (new File(_pathSeed).length() >= 16) {
											FileInputStream _stream = new FileInputStream(_pathSeed);
											_stream.read(_seed, 0, 16);
											_stream.close();
										}
									}
									catch(Exception x) {}
									
									String _answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();
									if (_answer.startsWith("/") != true) _answer = "/sdcard/" + _answer;
									try {
										FileOutputStream _stream = new FileOutputStream(_answer);
										byte[] _header = {'e', 't', 'x', 't', 0, 0, 0};
										_stream.write(_header);
										_stream.write(documentEncrypt(
											((EditText2)_boxDocument3.getChildAt(0)).getText().toString().getBytes("UTF-8"),
											_passphrase.getBytes("UTF-8"),
											_seed
										));
										_stream.close();
										
										Toast.makeText(getApplicationContext(), "Encrypted and saved as: " + _answer, 1).show();
										_valueDocumentHere = _answer;
										_boxToolbar.invalidate();
									}
									catch(Exception x) {x.printStackTrace(); return;}
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-picture-path") == 0) {
								if (_this1.label.compareTo("Load") == 0) {
									String _answer = null;
									try {_answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();} catch(Exception x) {return;}
									
									if (_answer != null) zoneDo("load", _answer);
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-music-path") == 0) {
								if (_this1.label.compareTo("Load") == 0) {
									String _answer = null;
									try {_answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();} catch(Exception x) {return;}
									
									if (_answer != null) zoneDo("load", _answer);
								}
								
								dismissDialog(0);
							}
							else if (_dialog1.label.compareTo("ask-web-path") == 0) {
								if (_this1.label.compareTo("Load") == 0) {
									String _answer = null;
									try {_answer = ((EditText2)_dialog1.getChildAt(0)).getText().toString();} catch(Exception x) {return;}
									
									if (_answer != null) zoneDo("load", _answer);
								}
								
								dismissDialog(0);
							}
						}})
				);
			}
			else if (type == 2) {
				String _name = null;
				try {_name = new File(label).getName();} catch(Exception x) {}
				
				_stick.in(
					new TextView2(this).label(label).width(_overall.width - (_heightSpan * 2)).height(_heightSpan * 2).text(_name).
						looks(new Looks() {public void action(View _this, Canvas canvas) {
							TextView2 _this1 = (TextView2)_this;
							Paint _drawer = new Paint();
							_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
							_drawer.setColor((new File(_this1.label).listFiles() == null) ? 0xffffffff : 0xffffff7f);
							_drawer.setTextSize(_heightSpan);
							canvas.drawText(_this1.getText().toString(), _heightSpan / 5, _heightSpan * 1.375f, _drawer);
						}}).
						whenClicked(new View.OnClickListener() {public void onClick(View _this) {
							TextView2 _this1 = (TextView2)_this;
							zoneDo("load", _this1.label);
						}})
				);
			}
			else {
				_stick.in(
					new TextView2(this).label(label).width(_overall.width - (_heightSpan * 2)).height(_heightSpan * 2).
						looks(new Looks() {public void action(View _this, Canvas canvas) {
							TextView2 _this1 = (TextView2)_this;
							Paint _drawer = new Paint();
							_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
							_drawer.setColor(0xffffffff);
							_drawer.setTextSize(_heightSpan);
							canvas.drawText(_this1.label, _heightSpan / 5, _heightSpan * 1.375f, _drawer);
						}})
				);
			}
			
			/* Icon (right) */
			if (icon2 != null) {
				if (type == 1) {
					TextView2 _label = (TextView2)_stick.getChildAt(icon1 != null ? 1 : 0);
					_label.width(_label.width - (_heightSpan * 2));
				}
				else {
					TextView2 _label = (TextView2)_stick.getChildAt(icon1 != null ? 1 : 0);
					_label.width(_label.width - (_heightSpan * 2));
				}
				_stick.in(buttonIcon(10, icon2));
			}
			
		return _stick;
	}
	
	private LinearLayout3 chapter2_Toolbar() {
		return new LinearLayout3(this).width(_overall.width).height(_heightSpan * 2).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				LinearLayout3 _this1 = (LinearLayout3)_this;
				
				/* Overall background color */
				canvas.drawColor(0xffffffff);
				
				/* Zone select button */
				_helperDraw.icon(canvas, 0, 0, 0xff000000, _overall.label);
				
				/* Currently you are in... */
				_helperDraw.rectangle(canvas, _heightSpan * 2, _heightSpan / 10, _this1.width - (_heightSpan * 2), (_heightSpan * 2) - (_heightSpan / 10), 0, 0, 0xff000000, false);
				String _here = "?";
				if (_overall.label.compareTo("folder") == 0) _here = _valueFilesHere;
				else if (_overall.label.compareTo("paper") == 0) _here = (_valueDocumentHere != null) ? _valueDocumentHere : "Untitled";
				else if (_overall.label.compareTo("mountain") == 0) _here = (_valuePictureHere != null) ? _valuePictureHere : "No loaded picture";
				else if (_overall.label.compareTo("repeat") == 0) _here = (_valueMusicHere != null) ? _valueMusicHere : "No loaded music";
				else if (_overall.label.compareTo("globe") == 0) _here = _valueWebHere;
				try {
					_helperDraw.text(canvas,
						_overall.width - (_heightSpan * 4) - ((_heightSpan / 5) * 2), _heightSpan * 2,
						(_heightSpan * 2) + (_heightSpan / 5), _heightSpan * 1.375f,
						0xff000000, _here, Paint.Align.LEFT, true
					);
				}
				catch(Exception x) {}
				
				/* Menu button */
				_helperDraw.icon(canvas, _this1.width - (_heightSpan * 2), 0, 0xff000000, "3x3");
			}}).
			in(buttonIcon(100, "zoneselect")).
			in(buttonIcon(200, "address")).
			in(buttonIcon(100, "zonemenu"));
	}
	private LinearLayout3 menuitem2(String id) {
		return new LinearLayout3(this).label(id).width(_overall.width - (_heightSpan * 2)).height(_heightSpan * 2).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				LinearLayout3 _this1 = (LinearLayout3)_this;
				
				/* Overall background color */
				canvas.drawColor((_this1.label.compareTo("close") == 0) ? 0xffbf3f3f : 0xffffffff);
				
				/* Icon and label */
				String _icon = null; String _label = null;
					
					/* Close the dialog */
					if (_this1.label.compareTo("close") == 0) {_icon = "x"; _label = "Close this dialog";}
					
					/* For: Zone select menu */
					else if (_this1.label.compareTo("zone-files") == 0) {_icon = "folder"; _label = "File manager";}
					else if (_this1.label.compareTo("zone-document") == 0) {_icon = "paper"; _label = "Text editor";}
					else if (_this1.label.compareTo("zone-picture") == 0) {_icon = "mountain"; _label = "Image viewer";}
					else if (_this1.label.compareTo("zone-music") == 0) {_icon = "repeat"; _label = "Music player";}
					else if (_this1.label.compareTo("zone-web") == 0) {_icon = "globe"; _label = "Web browser";}
					else if (_this1.label.compareTo("zone-test") == 0) {_icon = "3x3"; _label = "Test zone";}
					
					/* For: File manager */
					else if (_this1.label.compareTo("files-new") == 0) {_icon = "3x3"; _label = "Create new";}
					else if (_this1.label.compareTo("files-selectall") == 0) {_icon = "3x3"; _label = "Select all";}
					else if (_this1.label.compareTo("files-deselectall") == 0) {_icon = "3x3"; _label = "Deselect all";}
					else if (_this1.label.compareTo("files-reverseselection") == 0) {_icon = "3x3"; _label = "Reverse selection";}
					else if (_this1.label.compareTo("files-copy") == 0) {_icon = "3x3"; _label = "Copy";}
					else if (_this1.label.compareTo("files-cut") == 0) {_icon = "3x3"; _label = "Cut";}
					else if (_this1.label.compareTo("files-paste") == 0) {_icon = "3x3"; _label = "Paste";}
					else if (_this1.label.compareTo("files-delete") == 0) {_icon = "3x3"; _label = "Delete";}
					
					/* For: File manager (Context menu) */
					else if (_this1.label.compareTo("context-internal") == 0) {_icon = "3x3"; _label = "Open internally";}
					else if (_this1.label.compareTo("context-copy") == 0) {_icon = "3x3"; _label = "Copy";}
					else if (_this1.label.compareTo("context-move") == 0) {_icon = "3x3"; _label = "Move / Rename";}
					else if (_this1.label.compareTo("context-delete") == 0) {_icon = "3x3"; _label = "Delete";}
					
					/* For: Text editor */
					else if (_this1.label.compareTo("document-new") == 0) {_icon = "3x3"; _label = "New session";}
					else if (_this1.label.compareTo("document-decrypt") == 0) {_icon = "3x3"; _label = "Load (encrypted)";}
					else if (_this1.label.compareTo("document-save") == 0) {_icon = "3x3"; _label = "Save";}
					else if (_this1.label.compareTo("document-encrypt") == 0) {_icon = "3x3"; _label = "Save (encrypted)";}
					
					/* For: Image viewer */
					else if (_this1.label.compareTo("picture-wallpaper") == 0) {_icon = "3x3"; _label = "Set as wallpaper";}
					
					/* For: Music player */
					else if (_this1.label.compareTo("music-playpause") == 0) {_icon = "3x3"; _label = "Play / Pause";}
					else if (_this1.label.compareTo("music-repeat") == 0) {_icon = "3x3"; _label = "Repeat: " + ((_music.isLooping() == true) ? "ON" : "OFF");}
					
					/* For: Music player (Not menu item) */
					else if (_this1.label.compareTo("music-title") == 0) {_icon = "3x3"; _label = musicProfile(_valueMusicHere, "title");}
					else if (_this1.label.compareTo("music-artist") == 0) {_icon = "3x3"; _label = musicProfile(_valueMusicHere, "artist");}
					else if (_this1.label.compareTo("music-album") == 0) {_icon = "3x3"; _label = musicProfile(_valueMusicHere, "album");}
					
					/* For: Web browser */
					else if (_this1.label.compareTo("web-clear") == 0) {_icon = "3x3"; _label = "Clear";}
					
					/* For: Test zone */
					//else if (_this1.label.compareTo("picture-wallpaper") == 0) {_icon = "3x3"; _label = "Set as wallpaper";}
				
				if (_icon != null) _helperDraw.icon(canvas, 0, 0, (_this1.label.compareTo("close") == 0) ? 0xffffffff : 0xff000000, _icon);
				if (_label != null) {
					_helperDraw.text(canvas,
						_overall.width - (_heightSpan * 4) - ((_heightSpan / 5) * 2), _heightSpan * 2,
						(_heightSpan * 2) + (_heightSpan / 5), _heightSpan * 1.375f,
						(_this1.label.compareTo("close") == 0) ? 0xffffffff : 0xff000000,
						_label, Paint.Align.LEFT, true
					);
				}
			}}).
			in(buttonIcon(100, "menuitem")).
			in(buttonIcon(200, "menuitem"));
	}
	private LinearLayout3 fileitem2(String path) {
		return new LinearLayout3(this).label(path).width(_overall.width - (_heightSpan * 2)).height(_heightSpan * 2).
			looks(new Looks() {public void action(View _this, Canvas canvas) {
				LinearLayout3 _this1 = (LinearLayout3)_this;
				
				/* Overall background color */
				canvas.drawColor((_this1.index == 1) ? 0xffbfbf3f : 0xffffffff);
				
				/* Icon, label, and context menu button */
				File _file = new File(path);
				try {
					_helperDraw.icon(canvas, 0, 0,
						(_file.isDirectory() == true) ? 0xffbf3f3f : 0xff000000,
						(_file.isDirectory() == true) ? "folder" : "paper"
					);
					_helperDraw.text(canvas,
						_overall.width - (_heightSpan * 4), _heightSpan * 2,
						(_heightSpan * 2) + (_heightSpan / 5), _heightSpan * 1.375f,
						(_file.isDirectory() == true) ? 0xffbf3f3f : 0xff000000,
						_file.getName(), Paint.Align.LEFT, true
					);
					_helperDraw.icon(canvas, _overall.width - (_heightSpan * 2), 0, (_file.isDirectory() == true) ? 0xffbf3f3f : 0xff000000, "3x3");
				}
				catch(Exception x) {}
			}}).
			in(buttonIcon(100, "fileselect")).
			in(buttonIcon(200, "filerun")).
			in(buttonIcon(100, "contextmenu"));
	}
}
