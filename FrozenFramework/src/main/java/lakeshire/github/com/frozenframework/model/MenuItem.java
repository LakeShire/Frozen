package lakeshire.github.com.frozenframework.model;

public class MenuItem {

    private boolean showArrow;
    private String title;
    private int pic;
    private Class clazz;
    private String detail;
    private boolean needUpdate;
    private Runnable onClickRunnable;

    public Runnable getOnClickRunnable() {
        return onClickRunnable;
    }

    public void setOnClickRunnable(Runnable onClickRunnable) {
        this.onClickRunnable = onClickRunnable;
    }

    public MenuItem(String title, int pic, Class clazz, boolean showArrow) {
        this.title = title;
        this.pic = pic;
        this.clazz = clazz;
        this.showArrow = showArrow;
    }

    public MenuItem(String title, int pic, Class clazz) {
        this(title, pic, clazz, false);
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isShowArrow() {
        return showArrow;
    }

    public void setShowArrow(boolean showArrow) {
        this.showArrow = showArrow;
    }

    public boolean isNeedUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean needUpdate) {
        this.needUpdate = needUpdate;
    }
}
