package nextstep.courses.domain;

import java.util.Arrays;

public class ImageInfo {

    private Long id;
    private String title;
    private long imageSize;
    private ImageType imageType;
    private final int width;
    private final int height;

    public ImageInfo(Long id, String title, long imageSize, String imageType, int width, int height) {
        if (imageSize > 1048576) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (Arrays.stream(ImageType.values()).noneMatch(type -> type.name().equals(imageType))) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용됩니다.");
        }
        if (width < 300 || height < 200 || !ratio()) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 합니다.");
        }

        this.id = id;
        this.title = title;
        this.imageSize = imageSize;
        this.imageType = ImageType.valueOf(imageType);
        this.width = width;
        this.height = height;
    }

    private boolean ratio() {
        return (double) this.width / this.height == 1.5;
    }
}
