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

/* Android modules. */
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.inputmethodservice.*;
import android.inputmethodservice.KeyboardView.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.provider.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.SurfaceHolder.*;
import android.webkit.*;
import android.widget.*;

/* Java modules. */
import java.io.*;
import java.lang.*;
import java.net.*;
import java.util.*;

/* Internal XML nodes. */
import whitestone8214.creole.R;


public class CreoleSatelite extends InputMethodService implements KeyboardView.OnKeyboardActionListener {
	KeyboardView _boxKeypad;
	boolean _stateShift, _stateControl, _stateAlt;
	int _stateLanguage;
	char _valueLastInput;
	
	/* Only for Korean keypads */
	char _valueWaitingMoeum;
	char _valueWaitingBatchimA, _valueWaitingBatchimB, _valueWaitingBatchimMixed;
	char _valueLastInputHotbatchimA, _valueLastInputHotbatchimB, _valueLastInputGyubbatchim;
	int _stateInputKorean;
	char _valueKoreanStacked;
	char _nthKoreanFirst, _nthKoreanSecond;
	char[] _listKoreanSecondSingle;
	int _stateLastInputIsMoeum;
	
	int[] _listKorean2Cho;
	int[] _listKorean2Jong;
	int _turnKorean2;
	char[] _slotKorean2Individual;
	char[] _slotKorean2Stacked;
	int _nthKorean2Cho;
	boolean _isJaeum;
	boolean _isMoeum;
	
	public View onCreateInputView() {
		_stateShift = false; _stateControl = false; _stateAlt = false;
		_stateLanguage = 0;
		_valueLastInput = 0;
		
		/* Only for Korean keypads */
		_valueWaitingMoeum = 0;
		_valueWaitingBatchimA = 0; _valueWaitingBatchimB = 0; _valueWaitingBatchimMixed = 0;
		_valueLastInputHotbatchimA = 0; _valueLastInputHotbatchimB = 0; _valueLastInputGyubbatchim = 0;
		_stateInputKorean = 0;
		_valueKoreanStacked = 0;
		_nthKoreanFirst = 0; _nthKoreanSecond = 0;
		_listKoreanSecondSingle = new char[]{0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xd, 0xe, 0x12, 0x13, 0x15};
		_stateLastInputIsMoeum = 0;
		
		_listKorean2Cho = new int[]{1, 2, 4, 7, 8, 9, 0x11, 0x12, 0x13, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e};
		_listKorean2Jong = new int[]{1, 2, 3, 4, 5, 6, 7, 9, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f, 0x10, 0x11, 0x12, 0x14, 0x15, 0x16, 0x17, 0x18, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e};
		_turnKorean2 = 0;
		_slotKorean2Individual = new char[]{0, 0, 0, 0, 0, 0};
		_slotKorean2Stacked = new char[]{0, 0, 0, 0, 0, 0};
		_nthKorean2Cho = 0;
		
		_boxKeypad = new KeyboardView(this, null);
		//loadKeypad((_stateShift == true) ? R.xml.keypad_default_shift : R.xml.keypad_default);
		loadKeypad(R.xml.keypad_default);
		
		return _boxKeypad;
	}
	
	public void onKey(int code, int[] codes) {
		char _code = (char)code;
		//Log.e("Creole", "SAY CODE " + Integer.toString(code));
		/*Log.e("Creole", "SAY N_CODES " + Integer.toString(codes.length));
		for (int _nthCode = 0; _nthCode < codes.length; _nthCode++) {
			Log.e("Creole", "SAY CODE_" + Integer.toString(_nthCode) + " " + Integer.toString(codes[_nthCode]));
		}*/
		boolean _pass = true;
		
		if (_stateLanguage == 2) {
			if (_code == 0x84) {
				// Forget all you have saw here
				_turnKorean2 = 0;
				
				// Language switcher
				//_stateLanguage += 1; if (_stateLanguage > 2) _stateLanguage = 0;
				_stateLanguage = (_stateLanguage == 0) ? 2 : 0;
				
				if (_stateLanguage == 2) loadKeypad((_stateShift == true) ? R.xml.keypad_korean2_shift : R.xml.keypad_korean2);
				else if (_stateLanguage == 1) loadKeypad((_stateShift == true) ? R.xml.keypad_korean_shift : R.xml.keypad_korean);
				else loadKeypad((_stateShift == true) ? R.xml.keypad_default_shift : R.xml.keypad_default);
				
				return;
			}
			else if (_code == 0x20) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				sendKeyChar(_code);
				return;
			}
			else if (_code == 8) {
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
				return;
			}
			else if (_code == 9) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
				return;
			}
			else if (_code == 0x10) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
				return;
			}
			else if ((_code == 0x80) || (_code == 0x81)) {
				_stateShift = (_stateShift == true) ? false : true;
				if (_stateLanguage == 2) loadKeypad((_stateShift == true) ? R.xml.keypad_korean2_shift : R.xml.keypad_korean2);
				else if (_stateLanguage == 1) loadKeypad((_stateShift == true) ? R.xml.keypad_korean_shift : R.xml.keypad_korean);
				else loadKeypad((_stateShift == true) ? R.xml.keypad_default_shift : R.xml.keypad_default);
				return;
			}
			else if (_code == 0x82) {
				_stateControl = (_stateControl == true) ? false : true;
				return;
			}
			else if (_code == 0x83) {
				_stateAlt = (_stateAlt == true) ? false : true;
				return;
			}
			else if (_code == 0x85) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT));
				return;
			}
			else if (_code == 0x86) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP));
				return;
			}
			else if (_code == 0x87) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT));
				return;
			}
			else if (_code == 0x88) {
				if (_turnKorean2 >= 1) sendKeyChar(_slotKorean2Stacked[_turnKorean2 - 1]);
				_turnKorean2 = 0;
				getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN));
				return;
			}
			
			chapter2ForKorean2(_code);
			
			return;
		}
		
		if ((_code >= 0x1100) && (_code <= 0x1112)) { // 자음
			if ((_valueLastInput >= 0x1161) && (_valueLastInput <= 0x11a7)) { // 모음 -> 자음
				if (_valueWaitingMoeum != 0) {sendKeyChar(_valueWaitingMoeum); _valueWaitingMoeum = 0;}
				_code = convertKoreanChosungJongsung(_code);
				_valueWaitingBatchimA = _code;
				_pass = false;
			}
			else if ((_valueLastInput >= 0x11a8) && (_valueLastInput <= 0x11c2)) { // 받침 -> 자음
				if (_valueWaitingBatchimMixed != 0) sendKeyChar(_valueWaitingBatchimMixed);
				else if (_valueWaitingBatchimA != 0) {
					_code = convertKoreanChosungJongsung(_code);
					char _batchim = mixKoreanBatchims(_valueWaitingBatchimA, _code);
					if (_batchim == _valueWaitingBatchimA) {
						sendKeyChar(_valueWaitingBatchimA);
						_code = convertKoreanChosungJongsung(_code);
					}
					else {
						_valueWaitingBatchimMixed = _batchim;
						_valueWaitingBatchimB = _code;
						_pass = false;
					}
				}
			}
		}
		else if ((_code >= 0x1161) && (_code <= 0x11a7)) { // 모음
			if ((_valueLastInput >= 0x1100) && (_valueLastInput <= 0x1112)) { // 자음 -> 모음
				if ((_code == 0x1169) || (_code == 0x116e) || (_code == 0x1173)) {
					_valueWaitingMoeum = _code;
					_pass = false;
				}
			}
			else if ((_valueLastInput >= 0x1161) && (_valueLastInput <= 0x11a7)) { // 모음 -> 모음
				char _moeum = mixKoreanMoeums(_valueLastInput, _code);
				if (_moeum == _valueLastInput) sendKeyChar(_valueWaitingMoeum);
				else {_code = _moeum; _valueWaitingMoeum = 0;}
			}
			else if ((_valueLastInput >= 0x11a8) && (_valueLastInput <= 0x11c2)) { // 받침 -> 모음
				if (_valueWaitingBatchimMixed != 0) {
					sendKeyChar(_valueWaitingBatchimA); _valueWaitingBatchimA = 0;
					sendKeyChar(convertKoreanChosungJongsung(_valueWaitingBatchimB)); _valueWaitingBatchimB = 0;
					_valueWaitingBatchimMixed = 0;
				}
				else if (_valueWaitingBatchimA != 0) {
					sendKeyChar(convertKoreanChosungJongsung(_valueWaitingBatchimA)); _valueWaitingBatchimA = 0;
				}
				_valueWaitingMoeum = _code;
				_pass = false;
			}
		}
		else {
			if (_code < 0x80) {
				if (_valueWaitingBatchimMixed != 0) sendKeyChar(_valueWaitingBatchimMixed);
				else if (_valueWaitingBatchimA != 0) sendKeyChar(_valueWaitingBatchimA);
				else if (_valueWaitingMoeum != 0) sendKeyChar(_valueWaitingMoeum);
			}
		}
		
		if (_code == 0x08) {
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
			_valueWaitingMoeum = 0;
			_valueWaitingBatchimA = 0;
			_valueWaitingBatchimB = 0;
			_valueWaitingBatchimMixed = 0;
			return;
		}
		else if (_code == 0x09) {
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_TAB));
			_valueWaitingMoeum = 0;
			_valueWaitingBatchimA = 0;
			_valueWaitingBatchimB = 0;
			_valueWaitingBatchimMixed = 0;
			return;
		}
		else if (_code == 0x10) {
			// Enter
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
			_valueWaitingMoeum = 0;
			_valueWaitingBatchimA = 0;
			_valueWaitingBatchimB = 0;
			_valueWaitingBatchimMixed = 0;
			return;
		}
		else if ((_code == 0x80) || (_code == 0x81)) {
			_stateShift = (_stateShift == true) ? false : true;
			if (_stateLanguage == 2) loadKeypad((_stateShift == true) ? R.xml.keypad_korean2_shift : R.xml.keypad_korean2);
			else if (_stateLanguage == 1) loadKeypad((_stateShift == true) ? R.xml.keypad_korean_shift : R.xml.keypad_korean);
			else loadKeypad((_stateShift == true) ? R.xml.keypad_default_shift : R.xml.keypad_default);
			return;
		}
		else if (_code == 0x82) {
			// Control
			_stateControl = (_stateControl == true) ? false : true;
			return;
		}
		else if (_code == 0x83) {
			// Alt
			_stateAlt = (_stateAlt == true) ? false : true;
			return;
		}
		else if (_code == 0x84) {
			// Language switcher
			//_stateLanguage += 1; if (_stateLanguage > 2) _stateLanguage = 0;
			_stateLanguage = (_stateLanguage == 0) ? 2 : 0;
			
			if (_stateLanguage == 2) loadKeypad((_stateShift == true) ? R.xml.keypad_korean2_shift : R.xml.keypad_korean2);
			else if (_stateLanguage == 1) loadKeypad((_stateShift == true) ? R.xml.keypad_korean_shift : R.xml.keypad_korean);
			else loadKeypad((_stateShift == true) ? R.xml.keypad_default_shift : R.xml.keypad_default);
			
			return;
		}
		else if (_code == 0x85) {
			// Left arrow
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_LEFT));
			return;
		}
		else if (_code == 0x86) {
			// Up arrow
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_UP));
			return;
		}
		else if (_code == 0x87) {
			// Right arrow
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_RIGHT));
			return;
		}
		else if (_code == 0x88) {
			// Down arrow
			getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DPAD_DOWN));
			return;
		}
		
		if (_stateLanguage != 0) _valueLastInput = _code;
		if (_pass == true) {
			sendKeyChar(_code);
			_valueWaitingMoeum = 0;
			_valueWaitingBatchimA = 0;
			_valueWaitingBatchimB = 0;
			_valueWaitingBatchimMixed = 0;
		}
	}
	public boolean onKeyDown(int code, KeyEvent event) {
		//return true;
		return super.onKeyDown(code, event);
	}
	public boolean onKeyUp(int code, KeyEvent event) {
		//return true;
		return super.onKeyUp(code, event);
	}
	
	/* Seems no need to fill in */
	public void onPress(int code) {}
	public void onRelease(int code) {}
	public void onText(CharSequence text) {}
	public void swipeDown() {}
	public void swipeLeft() {}
	public void swipeRight() {}
	public void swipeUp() {}
	
	void doNothing() {char _n = 0;}
	void loadKeypad(int keypad) {
		Keyboard _keypad = new Keyboard(this, keypad);
		_boxKeypad.setKeyboard(_keypad);
		_boxKeypad.setOnKeyboardActionListener(this);
	}
	char convertKoreanChosungJongsung(char code) {
		// 초성 -> 종성
		if (code == 0x1100) return 0x11a8;
		else if (code == 0x1101) return 0x11a9;
		else if (code == 0x1102) return 0x11ab;
		else if (code == 0x1103) return 0x11ae;
		else if (code == 0x1105) return 0x11af;
		else if (code == 0x1106) return 0x11b7;
		else if (code == 0x1107) return 0x11b8;
		else if (code == 0x1109) return 0x11ba;
		else if (code == 0x110a) return 0x11bb;
		else if (code == 0x110b) return 0x11bc;
		else if (code == 0x110c) return 0x11bd;
		else if (code == 0x110e) return 0x11be;
		else if (code == 0x110f) return 0x11cf;
		else if (code == 0x1110) return 0x11c0;
		else if (code == 0x1111) return 0x11c1;
		else if (code == 0x1112) return 0x11c2;
		
		// 종성 -> 초성
		else if (code == 0x11a8) return 0x1100;
		else if (code == 0x11a9) return 0x1101;
		else if (code == 0x11ab) return 0x1102;
		else if (code == 0x11ae) return 0x1103;
		else if (code == 0x11af) return 0x1105;
		else if (code == 0x11b7) return 0x1106;
		else if (code == 0x11b8) return 0x1107;
		else if (code == 0x11ba) return 0x1109;
		else if (code == 0x11bb) return 0x110a;
		else if (code == 0x11bc) return 0x110b;
		else if (code == 0x11bd) return 0x110c;
		else if (code == 0x11be) return 0x110e;
		else if (code == 0x11bf) return 0x110f;
		else if (code == 0x11c0) return 0x1110;
		else if (code == 0x11c1) return 0x1111;
		else if (code == 0x11c2) return 0x1112;
		
		return code;
	}
	char mixKoreanBatchims(char batchimA, char batchimB) {
		if (batchimA == 0x11a8) {
			if (batchimB == 0x1100) return 0x11a9;
			else if (batchimB == 0x1109) return 0x11aa;
		}
		else if (batchimA == 0x11ab) {
			if (batchimB == 0x110c) return 0x11ac;
			else if (batchimB == 0x1112) return 0x11ad;
		}
		else if (batchimA == 0x11af) {
			if (batchimB == 0x1100) return 0x11b0;
			else if (batchimB == 0x1106) return 0x11b1;
			else if (batchimB == 0x1107) return 0x11b2;
			else if (batchimB == 0x1109) return 0x11b3;
			else if (batchimB == 0x1110) return 0x11b4;
			else if (batchimB == 0x1111) return 0x11b5;
			else if (batchimB == 0x1112) return 0x11b6;
		}
		else if (batchimA == 0x11b8) {
			if (batchimB == 0x1109) return 0x11b9;
		}
		
		return batchimA;
	}
	char mixKoreanMoeums(char moeumA, char moeumB) {
		if (moeumA == 0x1169) {
			if (moeumB == 0x1161) return 0x116a;
			else if (moeumB == 0x1162) return 0x116b;
			else if (moeumB == 0x1175) return 0x116c;
		}
		else if (moeumA == 0x116e) {
			if (moeumB == 0x1165) return 0x116f;
			else if (moeumB == 0x1166) return 0x1170;
			else if (moeumB == 0x1175) return 0x1171;
		}
		else if (moeumA == 0x1173) {
			if (moeumB == 0x1175) return 0x1174;
		}
		
		return moeumA;
	}
	
	void chapter2ForKorean2(char keycode) {
		Log.e("Creole", "KOREAN2_TURN " + Integer.toString(_turnKorean2));
		//Log.e("Creole", "KOREAN2_CODE " + Integer.toString(keycode, 16));
		//Log.e("Creole", "AAAAARGH");
		//Log.e("Creole", "VERYGOOD");
		
		if (_turnKorean2 == 0) {
			if (keycode >= 0x314f) {sendKeyChar(keycode); return;}
			
			_slotKorean2Individual[0] = keycode;
			_slotKorean2Stacked[0] = keycode;
		}
		else if (_turnKorean2 == 1) {
			if (keycode < 0x314f) {sendKeyChar(_slotKorean2Stacked[0]); _turnKorean2 = 0; chapter2ForKorean2(keycode); return;}
			
			_slotKorean2Individual[1] = keycode;
			_slotKorean2Stacked[1] = (char)(0xac00 + (0x024c * nthChoForKorean2(_slotKorean2Individual[0])) + (0x1c * (keycode - 0x314f)));
		}
		else if (_turnKorean2 == 2) {
			char _mixed = mixJoongForKorean2(_slotKorean2Individual[1], keycode);
			
			if ((keycode >= 0x314f) && (_mixed == 0)) {sendKeyChar(_slotKorean2Stacked[1]); _turnKorean2 = 0; chapter2ForKorean2(keycode); return;}
			
			_slotKorean2Individual[2] = keycode;
			if (keycode < 0x314f) _slotKorean2Stacked[2] = (char)(_slotKorean2Stacked[1] + (nthJongForKorean2(keycode) + 1));
			else _slotKorean2Stacked[2] = (char)(0xac00 + (0x024c * nthChoForKorean2(_slotKorean2Individual[0])) + (0x1c * (_mixed - 0x314f)));
		}
		else if (_turnKorean2 == 3) {
			char _previous = _slotKorean2Individual[2];
			char _mixed = mixJongForKorean2(_previous, keycode);
			
			if (keycode >= 0x314f) {
				if (_previous < 0x314f) {sendKeyChar(_slotKorean2Stacked[1]); _slotKorean2Individual[0] = _previous; _slotKorean2Stacked[0] = _previous; _turnKorean2 = 1;}
				else {sendKeyChar(_slotKorean2Stacked[2]); _slotKorean2Individual[0] = keycode; _slotKorean2Stacked[0] = keycode; _turnKorean2 = 0;}
				chapter2ForKorean2(keycode); return;
			}
			if ((_previous < 0x314f) && (_mixed == 0)) {
				sendKeyChar(_slotKorean2Stacked[2]); _slotKorean2Individual[0] = keycode; _slotKorean2Stacked[0] = keycode; _turnKorean2 = 0; chapter2ForKorean2(keycode); return;
			}
			
			_slotKorean2Individual[3] = keycode;
			if (_previous < 0x314f) _slotKorean2Stacked[3] = (char)(_slotKorean2Stacked[1] + (nthJongForKorean2(_mixed) + 1));
			else _slotKorean2Stacked[3] = (char)(_slotKorean2Stacked[2] + (nthJongForKorean2(keycode) + 1));
		}
		else if (_turnKorean2 == 4) {
			char _previous = _slotKorean2Individual[3];
			char _mixed = mixJongForKorean2(_previous, keycode);
			
			if (keycode < 0x314f) {
				if (_mixed != 0) {_slotKorean2Individual[4] = keycode; _slotKorean2Stacked[4] = (char)(_slotKorean2Stacked[2] + (nthJongForKorean2(_mixed) + 1));}
				else {sendKeyChar(_slotKorean2Stacked[3]); _slotKorean2Individual[0] = keycode; _slotKorean2Stacked[0] = keycode; _turnKorean2 = 0; chapter2ForKorean2(keycode); return;}
			}
			else {sendKeyChar(_slotKorean2Stacked[2]); _slotKorean2Individual[0] = _previous; _slotKorean2Stacked[0] = _previous; _turnKorean2 = 1; chapter2ForKorean2(keycode); return;}
		}
		else if (_turnKorean2 == 5) {
			char _previous = _slotKorean2Individual[4];
			/*if (keycode < 0x314f) {sendKeyChar(_slotKorean2Stacked[3]); _slotKorean2Individual[0] = _previous; _slotKorean2Stacked[0] = _previous; _turnKorean2 = 1;}
			else {sendKeyChar(_slotKorean2Stacked[4]); _slotKorean2Individual[0] = keycode; _slotKorean2Stacked[0] = keycode; _turnKorean2 = 0;}
			chapter2ForKorean2(keycode); return;*/
			
			if (keycode < 0x314f) {sendKeyChar(_slotKorean2Stacked[4]); _slotKorean2Individual[0] = keycode; _slotKorean2Stacked[0] = keycode; _turnKorean2 = 0;}
			else {sendKeyChar(_slotKorean2Stacked[3]); _slotKorean2Individual[0] = _previous; _slotKorean2Stacked[0] = _previous; _turnKorean2 = 1;}
			chapter2ForKorean2(keycode); return;
		}
		
		_turnKorean2++;
	}
	char mixJoongForKorean2(char joongA, char joongB) {
		if (joongA == 0x3157) {
			if (joongB == 0x314f) return (char)(joongA + 1);
			else if (joongB == 0x3150) return (char)(joongA + 2);
			else if (joongB == 0x3163) return (char)(joongA + 3);
		}
		else if (joongA == 0x315c) {
			if (joongB == 0x3153) return (char)(joongA + 1);
			else if (joongB == 0x3154) return (char)(joongA + 2);
			else if (joongB == 0x3163) return (char)(joongA + 3);
		}
		else if (joongA == 0x3161) {
			if (joongB == 0x3163) return (char)(joongA + 1);
		}
		
		return 0;
	}
	char mixJongForKorean2(char jongA, char jongB) {
		Log.e("Creole", "MIX_JONG " + Integer.toString(jongA, 16) + " " + Integer.toString(jongB, 16));
		if (jongA == 0x3131) {
			if (jongB == 0x3145) return (char)(jongA + 2);
		}
		else if (jongA == 0x3134) {
			if (jongB == 0x3148) return (char)(jongA + 1);
			else if (jongB == 0x314e) return (char)(jongA + 2);
		}
		else if (jongA == 0x3139) {
			if (jongB == 0x3131) return (char)(jongA + 1);
			else if (jongB == 0x3141) return (char)(jongA + 2);
			else if (jongB == 0x3142) return (char)(jongA + 3);
			else if (jongB == 0x3145) return (char)(jongA + 4);
			else if (jongB == 0x314c) return (char)(jongA + 5);
			else if (jongB == 0x314e) return (char)(jongA + 6);
		}
		else if (jongA == 0x3142) {
			if (jongB == 0x3145) return (char)(jongA + 2);
		}
		
		return 0;
	}
	int nthChoForKorean2(char keycode) {
		for (int _nth = 0; _nth < _listKorean2Cho.length; _nth++) {if (keycode == 0x3130 + _listKorean2Cho[_nth]) return _nth;}
		
		return -1;
	}
	int nthJongForKorean2(char keycode) {
		for (int _nth = 0; _nth < _listKorean2Jong.length; _nth++) {if (keycode == 0x3130 + _listKorean2Jong[_nth]) return _nth;}
		
		return -1;
	}
}
