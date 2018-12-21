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
import android.hardware.display.*;
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


public class DrawHelper {
	private int widthApp;
	private int heightApp;
	private int nxn;
	private int widthSpan;
	private int heightSpan;
	private int widthMargin;
	private int heightMargin;
	
	
	public DrawHelper(int _widthApp, int _heightApp, int _nxn) {
		widthApp = _widthApp;
		heightApp = _heightApp;
		nxn = _nxn;
		widthSpan = widthApp / nxn;
		heightSpan = heightApp / nxn;
		widthMargin = widthApp % nxn;
		heightMargin = heightApp % nxn;
	}
	
	public DrawHelper rectangle(Canvas canvas, float left, float top, float right, float bottom, float radiusX, float radiusY, int color, boolean fill) {
		if (canvas == null) return this;
		
		Paint _drawer = new Paint();
			_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
			_drawer.setColor(color);
			if (fill == true) _drawer.setStyle(Paint.Style.FILL_AND_STROKE);
			else _drawer.setStyle(Paint.Style.STROKE);
		canvas.drawRoundRect(new RectF(left, top, right, bottom), radiusX, radiusY, _drawer);
		
		return this;
	}
	public DrawHelper text(Canvas canvas, int width, int height, float x, float y, int color, String text, Paint.Align alignment, boolean fitToWidth) {
		if (canvas == null) return this;
		if (text == null) return this;
		
		Paint _drawer = new Paint();
			_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
			_drawer.setColor(color);
			_drawer.setTextSize(heightSpan);
			_drawer.setTextAlign(alignment);
		Path _path = new Path();
			_path.moveTo(x, y);
			_path.lineTo(x + width, y);
			
		String _text = text;
		if (fitToWidth == true) {
			for (int _nth = 0; _nth < text.length(); _nth++) {
				float _width = _drawer.measureText(text, 0, _nth + 1);
				if (_width >= width) {
					_text = text.substring(0, _nth);
					break;
				}
			}
		}
		canvas.drawTextOnPath(_text, _path, 0, 0, _drawer);
		
		return this;
	}
	public DrawHelper icon(Canvas canvas, float x, float y, int color, String id) {
		if (canvas == null) return this;
		if (id == null) return this;
		
		float _center = heightSpan;
		float _1 = (heightSpan * 2) / 10;
		
		Paint _drawer = new Paint();
			_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
			_drawer.setColor(color);
			_drawer.setTextSize(heightSpan);
			
		if (id.compareTo("3x3") == 0) {
			_drawer.setStrokeWidth(_1);
			_drawer.setStyle(Paint.Style.STROKE);
			canvas.drawPoint(x + (_center - (_1 * 2)), y + (_center - (_1 * 2)), _drawer);
			canvas.drawPoint(x + (_center - (_1 * 2)), y + _center, _drawer);
			canvas.drawPoint(x + (_center - (_1 * 2)), y + (_center + (_1 * 2)), _drawer);
			canvas.drawPoint(x + _center, y + (_center - (_1 * 2)), _drawer);
			canvas.drawPoint(x + _center, y + _center, _drawer);
			canvas.drawPoint(x + _center, y + (_center + (_1 * 2)), _drawer);
			canvas.drawPoint(x + (_center + (_1 * 2)), y + (_center - (_1 * 2)), _drawer);
			canvas.drawPoint(x + (_center + (_1 * 2)), y + _center, _drawer);
			canvas.drawPoint(x + (_center + (_1 * 2)), y + (_center + (_1 * 2)), _drawer);
		}
		else if (id.compareTo("folder") == 0) {
			_drawer.setStrokeWidth(1);
			_drawer.setStyle(Paint.Style.FILL_AND_STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center - (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center - (_1 * 2)));
			_brush.lineTo(x + _center, y + (_center - (_1 * 2)));
			_brush.lineTo(x + (_center - _1), y + (_center - (_1 * 3)));
			_brush.close();
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("paper") == 0) {
			_drawer.setStrokeWidth(1);
			_drawer.setStyle(Paint.Style.FILL_AND_STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center - (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center - (_1 * 2)));
			_brush.lineTo(x + (_center + (_1 * 2)), y + (_center - (_1 * 3)));
			_brush.close();
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("mountain") == 0) {
			_drawer.setStrokeWidth(1);
			_drawer.setStyle(Paint.Style.FILL_AND_STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center + _1));
			_brush.lineTo(x + (_center - _1), y + (_center - _1));
			_brush.lineTo(x + _center, y + _center);
			_brush.lineTo(x + (_center + (_1 * 2)), y + (_center - (_1 * 2)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center - _1));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center - (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.close();
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("repeat") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + _center, y + (_center - (_1 * 3)));
			_brush.lineTo(x + _center, y + (_center + (_1 * 3)));
			canvas.drawPath(_brush, _drawer);
			
			_drawer.setStrokeWidth(_1);
			Path _brush2 = new Path();
			_brush2.moveTo(x + (_center + _1), y + (_center - (_1 * 3)));
			_brush2.lineTo(x + (_center + _1), y + (_center + (_1 * 3)));
			canvas.drawPath(_brush2, _drawer);
			
			canvas.drawPoint(x + (_center - _1), y + (_center - _1), _drawer);
			canvas.drawPoint(x + (_center - _1), y + (_center + _1), _drawer);
		}
		else if (id.compareTo("repeat") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + _center, y + (_center - (_1 * 3)));
			_brush.lineTo(x + _center, y + (_center + (_1 * 3)));
			canvas.drawPath(_brush, _drawer);
			
			_drawer.setStrokeWidth(_1);
			Path _brush2 = new Path();
			_brush2.moveTo(x + (_center + _1), y + (_center - (_1 * 3)));
			_brush2.lineTo(x + (_center + _1), y + (_center + (_1 * 3)));
			canvas.drawPath(_brush2, _drawer);
			
			canvas.drawPoint(x + (_center - _1), y + (_center - _1), _drawer);
			canvas.drawPoint(x + (_center - _1), y + (_center + _1), _drawer);
		}
		else if (id.compareTo("globe") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			
			_brush.moveTo(x + _center, y + _center);
			_brush.arcTo(new RectF(x + (_center - (_1 * 3)), y + (_center - (_1 * 3)), x + (_center + (_1 * 3)), y + (_center + (_1 * 3))), 0, 190, true);
			_brush.arcTo(new RectF(x + (_center - (_1 * 3)), y + (_center - (_1 * 3)), x + (_center + (_1 * 3)), y + (_center + (_1 * 3))), 180, 190, true);
			
			_brush.moveTo(x + (_center - (_1 * 1.5f)), y + (_center - (_1 * 2.5f)));
			_brush.lineTo(x + (_center - (_1 * 1.5f)), y + (_center + (_1 * 2.5f)));
			_brush.moveTo(x + _center, y + (_center - (_1 * 3)));
			_brush.lineTo(x + _center, y + (_center + (_1 * 3)));
			_brush.moveTo(x + (_center + (_1 * 1.5f)), y + (_center - (_1 * 2.5f)));
			_brush.lineTo(x + (_center + (_1 * 1.5f)), y + (_center + (_1 * 2.5f)));
			
			_brush.moveTo(x + (_center - (_1 * 2.5f)), y + (_center - (_1 * 1.5f)));
			_brush.lineTo(x + (_center + (_1 * 2.5f)), y + (_center - (_1 * 1.5f)));
			_brush.moveTo(x + (_center - (_1 * 3)), y + _center);
			_brush.lineTo(x + (_center + (_1 * 3)), y + _center);
			_brush.moveTo(x + (_center - (_1 * 2.5f)), y + (_center + (_1 * 1.5f)));
			_brush.lineTo(x + (_center + (_1 * 2.5f)), y + (_center + (_1 * 1.5f)));
			
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("cycle") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + _center, y + _center);
			_brush.arcTo(new RectF(x + (_center - (_1 * 2)), y + (_center - (_1 * 2)), x + (_center + (_1 * 2)), y + (_center + (_1 * 2))), -90, 285, true);
			_brush.moveTo(x + _center, y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center - _1), y + (_center - (_1 * 2)));
			_brush.lineTo(x + _center, y + (_center - _1));
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("turnup") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center + (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center - _1), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center - _1), y + (_center - (_1 * 2)));
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center - _1));
			_brush.lineTo(x + (_center - _1), y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center + _1), y + (_center - _1));
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("plus") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + _center);
			_brush.lineTo(x + (_center + (_1 * 3)), y + _center);
			_brush.moveTo(x + _center, y + (_center - (_1 * 3)));
			_brush.lineTo(x + _center, y + (_center + (_1 * 3)));
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("v") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center + _1));
			_brush.lineTo(x + (_center - _1), y + (_center + (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center - (_1 * 3)));
			canvas.drawPath(_brush, _drawer);
		}
		else if (id.compareTo("x") == 0) {
			_drawer.setStrokeWidth(_1 / 2);
			_drawer.setStyle(Paint.Style.STROKE);
			Path _brush = new Path();
			_brush.moveTo(x + (_center - (_1 * 3)), y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center + (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.moveTo(x + (_center + (_1 * 3)), y + (_center - (_1 * 3)));
			_brush.lineTo(x + (_center - (_1 * 3)), y + (_center + (_1 * 3)));
			_brush.close();
			canvas.drawPath(_brush, _drawer);
		}
		
		return this;
	}
	public DrawHelper bitmap(Canvas canvas, Bitmap picture, float x, float y) {
		Paint _drawer = new Paint();
			_drawer.setFlags(Paint.ANTI_ALIAS_FLAG);
		canvas.drawBitmap(picture, x, y, _drawer);
		
		return this;
	}
}
