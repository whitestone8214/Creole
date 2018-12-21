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
import whitestone8214.creole.CameraRoom;
import whitestone8214.creole.DrawHelper;
import whitestone8214.creole.VideoRoom;

/* Android modules. */
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.database.*;
import android.graphics.*;
import android.graphics.drawable.*;
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


public class SuperViewGroup extends ViewGroup {
	/* Interface(s) */
	public interface Next {public void action(View _this, int widthThis, int heightThis);}
	public interface Looks {public void action(View _this, Canvas canvas);}
	public interface Placing {public void action(View _this, boolean changed, int left, int top, int right, int bottom);}
	public interface TouchReaction {public boolean action(View _this, MotionEvent motion);}
	
	
	/* Variables: Basic profile */
	public Context context = null;
	public SuperViewGroup context(Context value) {context = value; return this;}
	public int index = 0;
	public SuperViewGroup index(int value) {index = value; return this;}
	public String label = null;
	public SuperViewGroup label(String value) {label = value; return this;}
	public int width = 1;
	public SuperViewGroup width(int value) {width = value; return this;}
	public int height = 1;
	public SuperViewGroup height(int value) {height = value; return this;}
	public boolean autoResize = true;
	public SuperViewGroup autoResize(boolean value) {autoResize = value; return this;}
	public Looks looks = null;
	public SuperViewGroup looks(Looks value) {looks = value; return this;}
	public Next next = null;
	public SuperViewGroup next(Next value) {next = value; return this;}
	
	/* Variables: Child(ren) and parent(if exists) */
	public int widthInside = 1;
	public SuperViewGroup widthInside(int value) {widthInside = value; return this;}
	public int heightInside = 1;
	public SuperViewGroup heightInside(int value) {heightInside = value; return this;}
	public int xSight = 1;
	public SuperViewGroup xSight(int value) {xSight = value; return this;}
	public int ySight = 1;
	public SuperViewGroup ySight(int value) {ySight = value; return this;}
	public Placing placing;
	public SuperViewGroup placing(Placing value) {placing = value; return this;}
	public SuperViewGroup in(View view) {try {addView(view);} catch(Exception x) {} return this;}
	public SuperViewGroup out(int nth) {try {removeViewAt(nth);} catch(Exception x) {} return this;}
	public SuperViewGroup child(int nth) {try {return (SuperViewGroup)getChildAt(nth);} catch(Exception x) {return null;}}
	public SuperViewGroup parent() {try {return (SuperViewGroup)getParent();} catch(Exception x) {return null;}}
	
	/* Variables: Motions (Mainly scrolling) */
	public int distanceTotal = 0;
	public SuperViewGroup distanceTotal(int value) {distanceTotal = value; return this;}
	public int directionX = 1;
	public SuperViewGroup directionX(int value) {directionX = value; return this;}
	public int directionY = 1;
	public SuperViewGroup directionY(int value) {directionY = value; return this;}
	public int xDistance = 0;
	public SuperViewGroup xDistance(int value) {xDistance = value; return this;}
	public int yDistance = 0;
	public SuperViewGroup yDistance(int value) {yDistance = value; return this;}
	public TouchReaction touchReaction = null;
	public SuperViewGroup touchReaction(TouchReaction value) {touchReaction = value; return this;}
	public TouchReaction snatchReaction = null;
	public SuperViewGroup snatchReaction(TouchReaction value) {snatchReaction = value; return this;}
	public int xSlideFrom = 0;
	public SuperViewGroup xSlideFrom(int value) {xSlideFrom = value; return this;}
	public int ySlideFrom = 0;
	public SuperViewGroup ySlideFrom(int value) {ySlideFrom = value; return this;}
	public int xSlideLast = 0;
	public SuperViewGroup xSlideLast(int value) {xSlideLast = value; return this;}
	public int ySlideLast = 0;
	public SuperViewGroup ySlideLast(int value) {ySlideLast = value; return this;}
	
	
	/* Methods */
	public SuperViewGroup(Context _context) {
		super(_context);
		context = _context;
		setWillNotDraw(false);
	}
	public void onMeasure(int widthNatural, int heightNatural) {
		if ((width <= 0) || (height <= 0)) {
			super.onMeasure(widthNatural, heightNatural);
			if (next != null) next.action(this, widthNatural, heightNatural);
		}
		else {
			super.onMeasure(width, height);
			if (autoResize != true) setMeasuredDimension(width, height);
			if (next != null) next.action(this, width, height);
		}
	}
	public void onDraw(Canvas canvas) {
		if (looks != null) looks.action(this, canvas);
		super.onDraw(canvas);
	}
	public void onLayout(boolean changed, int left, int top, int right, int bottom) {
		if (placing != null) placing.action(this, changed, left, top, right, bottom);
	}
	public boolean onTouchEvent(MotionEvent motion) {
		if (touchReaction != null) return touchReaction.action(this, motion);
		else return super.onTouchEvent(motion);
	}
	public boolean onInterceptTouchEvent(MotionEvent motion) {
		if (snatchReaction != null) return snatchReaction.action(this, motion);
		else return super.onInterceptTouchEvent(motion);
	}
	
	
	/* Presets */
	public Placing PLACE_HORIZONTALLY = new Placing() {public void action(View _this, boolean changed, int left, int top, int right, int bottom) {
		SuperViewGroup _this1 = ((SuperViewGroup)_this).widthInside(0).heightInside(0);
		
		for (int _nth = 0; _nth < _this1.getChildCount(); _nth++) {
			View _child = _this1.getChildAt(_nth);
			int _widthChild = _child.getMeasuredWidth();
			int _heightChild = _child.getMeasuredHeight();
			_child.layout(_this1.xSight + _this1.widthInside, _this1.ySight, _this1.xSight + _this1.widthInside + _widthChild, _this1.ySight + _heightChild);
			
			_this1.widthInside += _widthChild;
			if (_this1.heightInside < _heightChild) _this1.heightInside(_heightChild);
		}
	}};
	public Placing PLACE_VERTICALLY = new Placing() {public void action(View _this, boolean changed, int left, int top, int right, int bottom) {
		SuperViewGroup _this1 = ((SuperViewGroup)_this).widthInside(0).heightInside(0);
		
		for (int _nth = 0; _nth < _this1.getChildCount(); _nth++) {
			View _child = _this1.getChildAt(_nth);
			int _widthChild = _child.getMeasuredWidth();
			int _heightChild = _child.getMeasuredHeight();
			_child.layout(_this1.xSight, _this1.ySight + _this1.heightInside, _this1.xSight + _widthChild, _this1.ySight + _this1.heightInside + _heightChild);
			
			if (_this1.widthInside < _widthChild) _this1.widthInside(_widthChild);
			_this1.heightInside += _heightChild;
		}
	}};
	public TouchReaction ALLOW_HORIZONTAL_SCROLL = new TouchReaction() {public boolean action(View _this, MotionEvent motion) {
		SuperViewGroup _this1 = (SuperViewGroup)_this;
		
		if (motion.getAction() == MotionEvent.ACTION_DOWN) {
			_this1.xSlideLast = 0;
			_this1.xSlideFrom = (int)motion.getX();
		}
		else if (motion.getAction() == MotionEvent.ACTION_MOVE) {
			if (motion.getHistorySize() < 1) return true;
			
			int xSlideHere = (int)(motion.getX() - motion.getHistoricalX(0));
			_this1.xSlideLast = xSlideHere;
			
			if (_this1.width < _this1.widthInside) _this1.xSight(_this1.xSight + xSlideHere);
			_this1.requestLayout();
		}
		else if ((motion.getAction() == MotionEvent.ACTION_UP) || (motion.getAction() == MotionEvent.ACTION_CANCEL)) {
			ScrollAnimation _animation = new ScrollAnimation(context).
				host(_this1).xFrom(_this1.xSight).xTo(_this1.xSight + _this1.xSlideLast);
			_this1.setAnimation(_animation);
			_this1.startAnimation(_animation);
		}
		
		return true;
	}};
	public TouchReaction ALLOW_VERTICAL_SCROLL = new TouchReaction() {public boolean action(View _this, MotionEvent motion) {
		SuperViewGroup _this1 = (SuperViewGroup)_this;
		
		if (motion.getAction() == MotionEvent.ACTION_DOWN) {
			_this1.ySlideLast = 0;
			_this1.ySlideFrom = (int)motion.getY();
		}
		else if (motion.getAction() == MotionEvent.ACTION_MOVE) {
			if (motion.getHistorySize() < 1) return true;
			
			int _ySlideHere = (int)(motion.getY() - motion.getHistoricalY(0));
			_this1.ySlideLast = _ySlideHere;
			
			if (_this1.height < _this1.heightInside) _this1.ySight(_this1.ySight + _ySlideHere);
			_this1.requestLayout();
		}
		else if ((motion.getAction() == MotionEvent.ACTION_UP) || (motion.getAction() == MotionEvent.ACTION_CANCEL)) {
			ScrollAnimation _animation = new ScrollAnimation(context).
				host(_this1).yFrom(_this1.ySight).yTo(_this1.ySight + _this1.ySlideLast);
			_this1.setAnimation(_animation);
			_this1.startAnimation(_animation);
		}
		
		return true;
	}};
	public TouchReaction ALLOW_FREE_SCROLL = new TouchReaction() {public boolean action(View _this, MotionEvent motion) {
		SuperViewGroup _this1 = (SuperViewGroup)_this;
		
		if (motion.getAction() == MotionEvent.ACTION_DOWN) {
			_this1.xSlideLast = 0;
			_this1.ySlideLast = 0;
			_this1.xSlideFrom = (int)motion.getX();
			_this1.ySlideFrom = (int)motion.getY();
		}
		else if (motion.getAction() == MotionEvent.ACTION_MOVE) {
			if (motion.getHistorySize() < 1) return true;
			
			int _xSlideHere = (int)(motion.getX() - motion.getHistoricalX(0));
			int _ySlideHere = (int)(motion.getY() - motion.getHistoricalY(0));
			_this1.xSlideLast = _xSlideHere;
			_this1.ySlideLast = _ySlideHere;
			
			if (_this1.width < _this1.widthInside) _this1.xSight(_this1.xSight + _xSlideHere);
			if (_this1.height < _this1.heightInside) _this1.ySight(_this1.ySight + _ySlideHere);
			_this1.requestLayout();
		}
		else if ((motion.getAction() == MotionEvent.ACTION_UP) || (motion.getAction() == MotionEvent.ACTION_CANCEL)) {
			ScrollAnimation _animation = new ScrollAnimation(context).
				host(_this1).xFrom(_this1.xSight).yFrom(_this1.ySight).xTo(_this1.xSight + _this1.xSlideLast).yTo(_this1.ySight + _this1.ySlideLast);
			_this1.setAnimation(_animation);
			_this1.startAnimation(_animation);
		}
		
		return true;
	}};
	
	
	/* Subclass(es) */
	public class ScrollAnimation extends Animation {
		public SuperViewGroup host;
		public float xFrom;
		public float yFrom;
		public float xTo;
		public float yTo;
		public float duration;
		
		public ScrollAnimation(Context context) {
			setDuration(500);
		}
		public ScrollAnimation host(SuperViewGroup value) {host = value; return this;}
		public ScrollAnimation xFrom(float value) {xFrom = value; return this;}
		public ScrollAnimation yFrom(float value) {yFrom = value; return this;}
		public ScrollAnimation xTo(float value) {xTo = value; return this;}
		public ScrollAnimation yTo(float value) {yTo = value; return this;}
		@Override public void applyTransformation(float ms, Transformation details) {
			if (host == null) return;
			
			/*float _xCrawl = (ms >= 1) ? 0 : ((xTo - xFrom) * (1 - ms));
			float _yCrawl = (ms >= 1) ? 0 : ((yTo - yFrom) * (1 - ms));*/
			float _xCrawl = (ms >= 1) ? 0 : (((xTo - xFrom) * 1.5f) * (1 - ms));
			float _yCrawl = (ms >= 1) ? 0 : (((yTo - yFrom) * 1.5f) * (1 - ms));
			if (host.width < host.widthInside) host.xSight += (int)_xCrawl;
			if (host.height < host.heightInside) host.ySight += (int)_yCrawl;
			
			if (ms >= 1) {
				if (host.xSight > 0) host.xSight(0);
				else if (host.widthInside <= host.width) {
					if (host.xSight < 0) host.xSight(0);
				}
				else if (host.xSight < 0 - (host.widthInside - host.width)) host.xSight(0 - (host.widthInside - host.width));
				
				if (host.ySight > 0) host.ySight(0);
				else if (host.heightInside <= host.height) {
					if (host.ySight < 0) host.ySight(0);
				}
				else if (host.ySight < 0 - (host.heightInside - host.height)) host.ySight(0 - (host.heightInside - host.height));
			}
			
			host.requestLayout();
		}
	}
}
