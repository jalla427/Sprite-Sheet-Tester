import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class spriteRun {
    private int height;
    private int width;
    private int rows;
    private int columns;
    private int fps;
    private int targetRow;
    private String filePath;
    private BufferedImage spriteSheet;

    public spriteRun(int height, int width, int rows, int columns, int fps, int targetRow, String filePath) {
        this.height = height;
        this.width = width;
        this.rows = rows;
        this.columns = columns;
        this.fps = fps;
        this.targetRow = targetRow;
        this.filePath = filePath;
    }

    public void loadImage() {
        try {
            BufferedImage image = ImageIO.read(new File(this.filePath));
            spriteSheet = image;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage grabImage(int row, int col, int width, int height) {
        BufferedImage img = spriteSheet.getSubimage((col * width) - width, (row * height) - height, width, height);
        return img;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public void setTargetRow(int targetRow) {
        this.targetRow = targetRow;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
