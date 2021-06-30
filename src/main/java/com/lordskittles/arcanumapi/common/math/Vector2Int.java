package com.lordskittles.arcanumapi.common.math;

public class Vector2Int
{
    public int x;
    public int y;

    public Vector2Int(double x, double y)
    {
        this.x = (int)x;
        this.y = (int)y;
    }

    public Vector2Int(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector2Int(Vector2Int vector)
    {
        this.x = vector.x;
        this.y = vector.y;
    }

    public Vector2Int(UVInt uv)
    {
        this.x = uv.u;
        this.y = uv.v;
    }

    public void update(Vector2Int pos)
    {
        this.x += pos.x;
        this.y += pos.y;
    }
}
