package info.emptycanvas.library.raytracer;

import info.emptycanvas.library.object.Point3D;

public class CCamera extends CNode {
    protected double mViewplaneDist;                                                // Distance du viexplane par rapport � la position de la cam.
    protected float mViewplaneWidth, mViewplaneHeight;                            // Largeur/Hauteur du viewplane.
    protected Point3D mCamPos = new Point3D(), mVecDir = new Point3D(), mUpVec = new Point3D(), mRightVec = new Point3D(), mViewPlaneUpLeft = new Point3D();

    public CCamera(Point3D vCamPos, Point3D direction, Point3D vRightVec, Point3D vUpVector, int type)

    {
        super(type, "CAMERA");
        mCamPos = vCamPos.norme1();
        mVecDir = direction.norme1();
        mUpVec = vUpVector.norme1();
        mRightVec = vRightVec.norme1();
        mViewplaneDist = 1.0f;
        mViewplaneHeight = 0.35f;
        mViewplaneWidth = 0.35f;
        mViewPlaneUpLeft = mCamPos.plus(direction.mult(mViewplaneDist))
                .plus(mRightVec.mult(-mViewplaneWidth/2)).plus(mUpVec.mult(-mViewplaneHeight/2));

    }

    Point3D calcDirVec(float x, float y, int xRes, int yRes) {
        double xIndent, yIndent;

        xIndent = mViewplaneWidth / (float) xRes;
        yIndent = mViewplaneHeight / (float) yRes;

        return mViewPlaneUpLeft.plus(mRightVec.mult(xIndent * x).plus(mUpVec.mult(- yIndent * y)));
    }


    @Override
    public boolean intersectsNode(CRay ray, CIntersectInfo intersectInfo) {
        return false;
    }

    public Point3D getPosition() {
        return mCamPos;
    }
}




