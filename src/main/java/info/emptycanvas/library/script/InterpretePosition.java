package info.emptycanvas.library.script;

import java.util.ArrayList;

import info.emptycanvas.library.object.Matrix33;
import info.emptycanvas.library.object.Point3D;
import info.emptycanvas.library.object.Barycentre;

public class InterpretePosition implements Interprete {

    private String repertoire;
    private int pos;

    public InterpretePosition() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public InterpreteConstants constant() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getPosition() {
        return pos;
    }

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        InterpretesBase bo;
        ArrayList<Integer> pattern;
        InterpretePoint3D bp;
        InterpreteMatrix33 bm;

        bo = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(bo.BLANK);
        pattern.add(bo.LEFTPARENTHESIS);
        pattern.add(bo.BLANK);
        pattern.add(bo.AROBASE);
        pattern.add(bo.BLANK);
        bo.compile(pattern);

        ArrayList<Object> os = bo.read(text, pos);

        pos = bo.getPosition();

        InterpreteIdentifier bi;
        bi = new InterpreteIdentifier();
        try {
            bi.interprete(text, pos);
            pos = bi.getPosition();
        } catch (InterpreteException ex) {

        }

        bp = new InterpretePoint3D();
        Point3D p = (Point3D) bp.interprete(text, pos);

        pos = bp.getPosition();

        bo = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(bo.BLANK);
        pattern.add(bo.MULTIPLICATION);
        pattern.add(bo.BLANK);
        bo.compile(pattern);

        os = bo.read(text, pos);
        pos = bo.getPosition();

        bi = new InterpreteIdentifier();
        try {
            bi.interprete(text, pos);
            pos = bi.getPosition();
        } catch (InterpreteException ex) {

        }
        bm = new InterpreteMatrix33();
        Matrix33 m = (Matrix33) bm.interprete(text, pos);
        pos = bm.getPosition();

        bo = new InterpretesBase();
        pattern = new ArrayList<Integer>();
        pattern.add(bo.BLANK);
        pattern.add(bo.MULTIPLICATION);
        pattern.add(bo.BLANK);
        pattern.add(bo.DECIMAL);
        pattern.add(bo.BLANK);
        pattern.add(bo.RIGHTPARENTHESIS);
        pattern.add(bo.BLANK);
        bo.compile(pattern);

        os = bo.read(text, pos);

        Double d = (Double) os.get(3);

        pos = bo.getPosition();

        this.pos = pos;

        Barycentre po = new Barycentre();

        po.position = p;
        po.rotation = m;
        po.agrandissement = d;
        return po;
    }

    @Override
    public void setConstant(InterpreteConstants c) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setRepertoire(String r) {
        this.repertoire = r;
    }

}
