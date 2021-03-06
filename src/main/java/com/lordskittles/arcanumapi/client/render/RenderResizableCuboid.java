package com.lordskittles.arcanumapi.client.render;

import com.lordskittles.arcanumapi.client.render.ArcaneRenderer.Model3D;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Arrays;

public class RenderResizableCuboid {

    public static final RenderResizableCuboid INSTANCE = new RenderResizableCuboid();

    private static final Vector3f V_ZERO = new Vector3f(0, 0, 0);
    private static final int U_MIN = 0;
    private static final int U_MAX = 1;
    private static final int V_MIN = 2;
    private static final int V_MAX = 3;

    protected EntityRendererManager manager = Minecraft.getInstance().getRenderManager();

    private static Vector3f withValue(Vector3f vector, Axis axis, float value) {

        if(axis == Axis.X) {
            return new Vector3f(value, vector.getY(), vector.getZ());
        }
        else
            if(axis == Axis.Y) {
                return new Vector3f(vector.getX(), value, vector.getZ());
            }
            else
                if(axis == Axis.Z) {
                    return new Vector3f(vector.getX(), vector.getY(), value);
                }
        throw new RuntimeException("Was given a null axis! That was probably not meant to happen, please consider this a bug! (Vector = " + vector + ")");
    }

    public static double getValue(Vector3d vector, Axis axis) {

        if(axis == Axis.X) {
            return vector.x;
        }
        else
            if(axis == Axis.Y) {
                return vector.y;
            }
            else
                if(axis == Axis.Z) {
                    return vector.z;
                }
        throw new RuntimeException("Was given a null axis! That was probably not meant to happen, please consider this a bug! (Vector = " + vector + ")");
    }

    public void renderCube(Model3D cube, MatrixStack matrix, IVertexBuilder buffer, int argb, int light) {

        float red = ArcaneRenderer.getRed(argb);
        float blue = ArcaneRenderer.getBlue(argb);
        float green = ArcaneRenderer.getGreen(argb);
        float alpha = ArcaneRenderer.getAlpha(argb);
        Vector3d size = new Vector3d(cube.sizeX(), cube.sizeY(), cube.sizeZ());

        matrix.push();
        matrix.translate(cube.min.x, cube.min.y, cube.min.z);
        Matrix4f matrix4f = matrix.getLast().getMatrix();

        for(Direction face : Direction.values()) {
            int ordinal = face.ordinal();
            TextureAtlasSprite sprite = cube.textures[ordinal];
            if(sprite != null) {
                Axis u = face.getAxis() == Axis.X ? Axis.Z : Axis.X;
                Axis v = face.getAxis() == Axis.Y ? Axis.Z : Axis.Y;
                float other = face.getAxisDirection() == Direction.AxisDirection.POSITIVE ? (float) getValue(size, face.getAxis()) : 0;

                //Swap the face if this is positive: the renderer returns indexes that ALWAYS are for the negative face, so light it properly this way
                face = face.getAxisDirection() == Direction.AxisDirection.NEGATIVE ? face : face.getOpposite();
                Direction opposite = face.getOpposite();

                float minU = sprite.getMinU();
                float maxU = sprite.getMaxU();
                //Flip the v
                float minV = sprite.getMaxV();
                float maxV = sprite.getMinV();
                double sizeU = getValue(size, u);
                double sizeV = getValue(size, v);
                for(int uIndex = 0; uIndex < sizeU; uIndex++) {
                    float[] baseUV = new float[] { minU, maxU, minV, maxV };
                    double addU = 1;
                    // If the size of the texture is greater than the cuboid goes on for then make sure the texture positions are lowered
                    if(uIndex + addU > sizeU) {
                        addU = sizeU - uIndex;
                        baseUV[U_MAX] = baseUV[U_MIN] + (baseUV[U_MAX] - baseUV[U_MIN]) * (float) addU;
                    }
                    for(int vIndex = 0; vIndex < sizeV; vIndex++) {
                        float[] uv = Arrays.copyOf(baseUV, 4);
                        double addV = 1;
                        if(vIndex + addV > sizeV) {
                            addV = sizeV - vIndex;
                            uv[V_MAX] = uv[V_MIN] + (uv[V_MAX] - uv[V_MIN]) * (float) addV;
                        }
                        float[] xyz = new float[] { uIndex, (float) (uIndex + addU), vIndex, (float) (vIndex + addV) };

                        renderPoint(matrix4f, buffer, face, u, v, other, uv, xyz, true, false, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, face, u, v, other, uv, xyz, true, true, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, face, u, v, other, uv, xyz, false, true, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, face, u, v, other, uv, xyz, false, false, red, green, blue, alpha, light);

                        renderPoint(matrix4f, buffer, opposite, u, v, other, uv, xyz, false, false, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, opposite, u, v, other, uv, xyz, false, true, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, opposite, u, v, other, uv, xyz, true, true, red, green, blue, alpha, light);
                        renderPoint(matrix4f, buffer, opposite, u, v, other, uv, xyz, true, false, red, green, blue, alpha, light);
                    }
                }
            }
        }

        matrix.pop();
    }

    private void renderPoint(Matrix4f matrix4f, IVertexBuilder buffer, Direction face, Axis u, Axis v, float other, float[] uv, float[] xyz, boolean minU, boolean minV,
                             float red, float green, float blue, float alpha, int light) {

        int U_ARRAY = minU ? U_MIN : U_MAX;
        int V_ARRAY = minV ? V_MIN : V_MAX;
        Vector3f vertex = withValue(V_ZERO, u, xyz[U_ARRAY]);
        vertex = withValue(vertex, v, xyz[V_ARRAY]);
        vertex = withValue(vertex, face.getAxis(), other);
        buffer.pos(matrix4f, vertex.getX(), vertex.getY(), vertex.getZ()).color(red, green, blue, alpha).tex(uv[U_ARRAY], uv[V_ARRAY]).lightmap(light).endVertex();
    }
}
