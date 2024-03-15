package edu.rpi.legup.puzzle.rippleeffect;

import edu.rpi.legup.model.PuzzleImporter;
import edu.rpi.legup.save.InvalidFileFormatException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.awt.Point;

public class RippleEffectImporter extends PuzzleImporter {
    public RippleEffectImporter(RippleEffect rippleEffect) {
        super(rippleEffect);
    }

    /**
     * Puzzle setting to support row and column inputs
     */
    @Override
    public boolean acceptsRowsAndColumnsInput() {
        return true;
    }

    /**
     * Puzzle setting to disable support for text input
     */
    @Override
    public boolean acceptsTextInput() {
        return false;
    }


    /**
     * Constructs empty RippleEffect gameboard as per the provided dimensions
     * @param rows number of rows for the gameboard
     * @param columns number of columns for the gameboard
     */
    @Override
    public void initializeBoard(int rows, int columns) {
        RippleEffectBoard rippleEffectBoard = new RippleEffectBoard(rows, columns);
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                // new ripple effect cell
                if (rippleEffectBoard.getCell(x, y) == null) {
                    RippleEffectCell cell = new RippleEffectCell(RippleEffectCellType.EMPTY.value, new Point(x, y));
                    cell.setIndex(y * columns + x);
                    cell.setModifiable(true);
                    rippleEffectBoard.setCell(x, y, cell);
                }
            }
        }
        puzzle.setCurrentBoard(rippleEffectBoard);
    }



    /**
     * Constructs RippleEffect gameboard
     * @param node xml document node
     * @throws InvalidFileFormatException if file is invalid
     */
    @Override
    public void initializeBoard(Node node) throws InvalidFileFormatException {
        try {
            if (node == null) throw new InvalidFileFormatException("Invalid format");
            if (!node.getNodeName().equalsIgnoreCase("board")) {
                throw new InvalidFileFormatException("lightup Importer: cannot find board puzzleElement");
            }
            Element boardElement = (Element) node;


        }
        catch (NumberFormatException e) {
            throw new InvalidFileFormatException("RippleEffect Importer: unknown value where integer expected");
        }

    }



    /**
     * Initialize board via string of statements.
     * @throws UnsupportedOperationException since RippleEffect does not support text input
     */
    @Override
    public void initializeBoard(String[] statements) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Ripple Effect does not accept text input");
    }
}
