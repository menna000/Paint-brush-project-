package paint;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.BasicStroke;
import java.awt.Point;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Paintbrushproj {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        DrawingPanel paintpanel = new DrawingPanel();
        frame.setContentPane(paintpanel);
        frame.setTitle("Paint Brush Project");
        frame.setSize(500, 500);
        frame.setVisible(true);
        paintpanel.setFocusable(true);
        paintpanel.requestFocusInWindow();

    }
}

class DrawingPanel extends JPanel {

    private int shapeCount = 0;
    private Shape currentShape;
    private Color currentColor = Color.black; // Default color
    private Pencil pencil = new Pencil();
    public DrawingPanel p;
    private Eraser eraser = new Eraser();
    ArrayList<Shape> shapes = new ArrayList<Shape>();
    private Cursor eraserCursor;

    public DrawingPanel() {
        setBackground(Color.WHITE);
        JButton lineButton = new JButton("Draw Line");
        JButton rectangleButton = new JButton("Draw Rectangle");
        JButton ovalButton = new JButton("Draw Oval");
        JButton redButton = new JButton("Red");
        JButton blueButton = new JButton("Blue");
        JButton greenButton = new JButton("Green");
        JButton blackButton = new JButton("Black");
        JButton clearButton = new JButton("Clear All");
        JButton freehandButton = new JButton("Pencil");
        JButton eraserButton = new JButton("Eraser");
        JButton undoButton = new JButton("Undo");
        JButton saveButton = new JButton("Save");
        JButton openButton = new JButton("Open");
        this.setFont(new Font("Arial", Font.PLAIN, 18));
        Checkbox chk1 = new Checkbox("Solid", false);
        Checkbox chk2 = new Checkbox("Dotted", false);
        redButton.setBackground(Color.RED);
        blueButton.setBackground(Color.BLUE);
        greenButton.setBackground(Color.GREEN);
        blackButton.setBackground(Color.BLACK);
        JLabel function = new JLabel("Functions: ");
        JLabel paint = new JLabel("Paint Mode: ");
        JLabel col = new JLabel("Colors: ");
        function.setFont(new Font("Arial", Font.PLAIN, 18));
        paint.setFont(new Font("Arial", Font.PLAIN, 18));
        col.setFont(new Font("Arial", Font.PLAIN, 18));

        Image cursorImage = createCursorImage();
        eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "EraserCursor");

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setCurrentShape(new Line());
                resetCursor();

            }

        });

        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentShape(new Rectangle());
                resetCursor();
            }
        });

        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentShape(new Oval());
                resetCursor();

            }
        });

        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentColor(Color.red);
                resetCursor();

            }
        });

        blueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentColor(Color.blue);
                resetCursor();

            }
        });

        greenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentColor(Color.green);
                resetCursor();
            }
        });

        chk1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                currentShape.setFill(chk1.getState());
                resetCursor();
            }
        });

        chk2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                currentShape.setDott(chk2.getState());
                resetCursor();
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shapes.clear();
                pencil.clearPoints();
                repaint();
                resetCursor();
            }
        });

        freehandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentShape(pencil);
                resetCursor();
            }
        });
        eraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentShape(eraser);
               
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = shapes.size() - 1;
                shapes.remove(index);
                repaint();
                resetCursor();
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveDrawing(DrawingPanel.this);
                resetCursor();
            }
        });
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openDrawing(DrawingPanel.this);
                resetCursor();
            }
        });
        blackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCurrentColor(Color.BLACK);
                resetCursor();
            }
        });
        add(openButton);
        add(saveButton);
        add(function);
        add(clearButton);
        add(undoButton);
        add(paint);
        add(lineButton);
        add(rectangleButton);
        add(ovalButton);
        add(freehandButton);
        add(eraserButton);
        add(chk1);
        add(chk2);
        add(col);
        add(redButton);
        add(blueButton);
        add(greenButton);
        add(blackButton);
        redButton.setBackground(Color.RED);
        blueButton.setBackground(Color.BLUE);
        greenButton.setBackground(Color.GREEN);
        blackButton.setBackground(Color.BLACK);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (currentShape != null) {
                    if (currentShape instanceof Eraser) {
                        Eraser newerase = new Eraser();
                        newerase.addPoint(e.getX(), e.getY());
                        setCurrentShape(newerase);
                        repaint();
                    } else {
                        currentShape.setStartX(e.getX());
                        currentShape.setStartY(e.getY());
                        currentShape.setEndX(e.getX());
                        currentShape.setEndY(e.getY());
                        currentShape.setColor(currentColor);
                        currentShape.setFill(chk1.getState());
                        currentShape.setDott(chk2.getState());

                        if (currentShape instanceof Pencil) {
                            Pencil newPencil = new Pencil();
                            newPencil.addPoint(e.getX(), e.getY());
                            setCurrentShape(newPencil);
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (currentShape != null) {
                    currentShape.setEndX(e.getX());
                    currentShape.setEndY(e.getY());
                    currentShape.setFill(chk1.getState());
                    currentShape.setDott(chk2.getState());
                    shapes.add(currentShape);
                    if (currentShape instanceof Line) {
                        currentShape = new Line();
                    } else if (currentShape instanceof Rectangle) {
                        currentShape = new Rectangle();
                    } else if (currentShape instanceof Oval) {
                        currentShape = new Oval();
                    } else if (currentShape instanceof Pencil) {
                        currentShape = new Pencil();
                    } else if (currentShape instanceof Eraser) {
                        currentShape = new Eraser();
                    }

                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    if (currentShape instanceof Eraser) {
                        ((Eraser) currentShape).addPoint(e.getX(), e.getY());
                        repaint();
                    } else if (currentShape instanceof Pencil) {
                        ((Pencil) currentShape).addPoint(e.getX(), e.getY());
                    } else {
                        currentShape.setEndX(e.getX());
                        currentShape.setEndY(e.getY());
                    }
                    repaint();

                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    private void saveDrawing(JPanel panel) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(panel);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            if (!selectedFile.getName().toLowerCase().endsWith(".png")) {
                selectedFile = new File(selectedFile.getParentFile(), selectedFile.getName() + ".png");
            }

            try {
                BufferedImage drawingImage = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics g = drawingImage.getGraphics();

                g.setColor(Color.WHITE);
                g.fillRect(0, 0, drawingImage.getWidth(), drawingImage.getHeight());

                for (Shape shape : shapes) {
                    shape.draw(g);
                }
                if (currentShape != null) {
                    currentShape.draw(g);
                }

                ImageIO.write(drawingImage, "png", selectedFile);
                JOptionPane.showMessageDialog(panel, "Drawing saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error saving drawing", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openDrawing(JPanel panel) {

        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(panel);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);

                if (image != null) {
                    ImageShape imageShape = new ImageShape(image);
                    shapes.add(imageShape);

                    repaint();
                } else {
                    JOptionPane.showMessageDialog(panel, "Error: Unable to load the image", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error opening image: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private Image createCursorImage() {
        BufferedImage cursorImage = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = cursorImage.createGraphics();

        // Draw a small white square with a black border
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 16, 16);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(0, 0, 15, 15);

        g2d.dispose();
        return cursorImage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < shapes.size(); i++) {
            shapes.get(i).draw(g);
        }
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    public void resetCursor() {
        setCursor(Cursor.getDefaultCursor());
    }

    public void setCurrentShape(Shape shape) {
        currentShape = shape;
        currentShape.setColor(currentColor);
        if (currentShape instanceof Eraser) {
            setCursor(eraserCursor);
        } else {
            resetCursor();
        }
    }

    public void setCurrentColor(Color color) {
        currentColor = color;
    }
}

abstract class Shape {

    protected int startX, startY, endX, endY;
    protected Color color = Color.black;
    boolean isFill = false;
    boolean isDott = false;

    public abstract void draw(Graphics g);

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFill(boolean isFill) {
        this.isFill = isFill;
    }

    public void setDott(boolean isDott) {
        this.isDott = isDott;
    }
}

class Line extends Shape {

    public void setDott(boolean isDott) {
        this.isDott = isDott;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        if (isDott) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            float[] dashPattern = {8f, 5f};
            BasicStroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);
            g2d.setStroke(dashedStroke);
            if (isFill == false) {
                g2d.drawLine(startX, startY, endX, endY);
            } else {
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(startX, startY, endX, endY);
            }

            g2d.setStroke(new BasicStroke());

        } else {
            g.setColor(color);
            if (isFill == false) {
                g.drawLine(startX, startY, endX, endY);
            } else {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(color);
                g2d.setStroke(new BasicStroke(5));
                g2d.drawLine(startX, startY, endX, endY);
            }
        }
    }
}

class Rectangle extends Shape {

    @Override
    public void draw(Graphics g) {
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);

        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);

        g.setColor(color);
        if (isDott) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            float[] dashPattern = {8f, 5f};
            BasicStroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);
            g2d.setStroke(dashedStroke);

            if (isFill == false) {
                g2d.drawRect(x, y, width, height);
            } else {
                g2d.fillRect(x, y, width, height);
            }

            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(color);
            if (isFill == false) {
                g.drawRect(x, y, width, height);
            } else {
                g.fillRect(x, y, width, height);
            }
        }
    }
}

class Oval extends Shape {

    @Override
    public void draw(Graphics g) {
        int x = Math.min(startX, endX);
        int y = Math.min(startY, endY);
        int width = Math.abs(endX - startX);
        int height = Math.abs(endY - startY);

        g.setColor(color);
        if (isDott) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);
            float[] dashPattern = {8f, 5f};
            BasicStroke dashedStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);
            g2d.setStroke(dashedStroke);

            if (isFill == false) {
                g2d.drawOval(x, y, width, height);
            } else {
                g2d.fillOval(x, y, width, height);
            }

            g2d.setStroke(new BasicStroke());
        } else {
            g.setColor(color);
            if (isFill == false) {
                g.drawOval(x, y, width, height);
            } else {
                g.fillOval(x, y, width, height);
            }
        }
    }
}

class Pencil extends Shape {

    private ArrayList<Point> points = new ArrayList<>();

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(2));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void clearPoints() {
        points.clear();
    }
}

class Eraser extends Shape {

    private ArrayList<Point> points = new ArrayList<>();

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        float strokeWidth = 20.0f;
        g2d.setStroke(new BasicStroke(strokeWidth));

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        g2d.setStroke(new BasicStroke());
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void erase(DrawingPanel drawingPanel) {
        Graphics g = drawingPanel.getGraphics();
        g.setColor(Color.WHITE);
        for (Point point : points) {
            g.fillRect(point.x, point.y, 1, 1);
        }
    }

    public void clearPoints() {
        points.clear();
    }
}

class ImageShape extends Shape {

    private BufferedImage image;

    public ImageShape(BufferedImage image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, startX, startY, null);
    }
}
