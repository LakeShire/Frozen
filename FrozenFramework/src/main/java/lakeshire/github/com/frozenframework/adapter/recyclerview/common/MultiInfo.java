package lakeshire.github.com.frozenframework.adapter.recyclerview.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 多类型信息
 *
 * @author lakeshire
 */
public class MultiInfo {
    public final static int TYPE_TEXT = 0;
    public final static int TYPE_SINGLE_IMAGE = 1;
    public final static int TYPE_MULTI_IMAGE = 2;
    public final static int TYPE_TEXT_AND_IMAGE = 3;

    public int type;

    public String title;
    public String description;
    public List<Integer> images = new ArrayList<>();

    public MultiInfo(String title, String description, int type) {
        this.title = title;
        this.description = description;
        this.type = type;
    }
}
