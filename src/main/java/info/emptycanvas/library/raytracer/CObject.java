package info.emptycanvas.library.raytracer;

/**
 * Created by manuel on 03-08-16.
 */
public abstract class CObject extends CNode {
    public static final int LIGHT = 0x0001000;
    public static final int OMNILIGHT = 0x0001002;
    public static final int CAMERA = 0x0010000;
    public static final int TARGETCAMERA = 0x0010002;

    protected CNode mNode;
    protected Matiere mMaterial;

    public CObject() {
        mNode = getNodeInstance();
    }

    // get
    public Matiere getMaterial() {
        return mMaterial;
    }

    public void setMaterial(Matiere material) {
        mMaterial = material;
    }

    public CNode getNode() {
        return mNode;
    }

    // set
    public void setNode(CNode node) {
        mNode = node;
    }

}
