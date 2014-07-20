package com.qoolander.MyRocket.block;

public class Vector3{
    public int x;
    public int y;
    public int z;


    public Vector3 clone(){
        //return this.clone();
        return new Vector3(this.x, this.y, this.z);
    }


    public Vector3(int _x, int _y, int _z){
        x = _x;
        y = _y;
        z = _z;
    }

}
