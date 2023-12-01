package nextstep.courses.domain;

public enum ImageType {

    gif("gif"),
    jpg("jpg"),
    jpeg("jpeg"),
    png("png"),
    svg("svg");

    private String imageType;

    ImageType(String imageType) {
        this.imageType = imageType;
    }
}
