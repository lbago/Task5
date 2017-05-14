package androidlab.epam.com.task5;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;


public class SmileView extends View {
    private Paint mCirclePaint;
    private Paint mEyeAndMouthPaint;

    private float mCenterX;
    private float mCenterY;
    private float mRadius;
    private RectF mArcBounds = new RectF();

    public SmileView(Context context) {
        this(context, null);
    }

    public SmileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SmileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    //инициализация кистей
    private void initPaints() {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.YELLOW);
        mEyeAndMouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mEyeAndMouthPaint.setStyle(Paint.Style.STROKE);
        mEyeAndMouthPaint.setStrokeWidth(25 * getResources().getDisplayMetrics().density);
        mEyeAndMouthPaint.setStrokeCap(Paint.Cap.ROUND);
        mEyeAndMouthPaint.setColor(Color.GRAY);
    }

    // Размеры изображения
    //Необходимо для того,чтобы родительский контейнер мог корректо отобразить изображение
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // рисуем лицо
        canvas.drawCircle(mCenterX, mCenterY, mRadius, mCirclePaint);
        // рисуем глаза
        float eyeRadius = mRadius / 8f;
        float eyeOffsetX = mRadius / 3f;
        float eyeOffsetY = mRadius / 3f;
        canvas.drawCircle(mCenterX - eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
        canvas.drawCircle(mCenterX + eyeOffsetX, mCenterY - eyeOffsetY, eyeRadius, mEyeAndMouthPaint);
        // рисуем рот
        float mouthInset = mRadius/2f;
        mArcBounds.set(mRadius/2f,mCenterY+mRadius/4f,3*mRadius/2,mCenterY+mRadius);
        canvas.drawArc(mArcBounds, 180f, 180f,false, mEyeAndMouthPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mCenterX = w / 2f;
        mCenterY = h / 2f;
        mRadius = Math.min(w, h) / 2f;
    }

}
