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

    @Override
    public boolean equals(Object o) {
        if(((Vector3)o).x == this.x){
            if(((Vector3)o).y == this.y){
                if(((Vector3)o).z == this.z){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }
}
