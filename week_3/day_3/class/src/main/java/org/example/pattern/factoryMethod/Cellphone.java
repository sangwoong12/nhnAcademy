package org.example.pattern.factoryMethod;

public class Cellphone implements Product {
    int modelNumber;
    String modelName;
    String category;

    public Cellphone(int modelNumber, String modelName){
        this.modelName = modelName;
        this.modelNumber = modelNumber;
    }
    public String getModelName(){
        return this.modelName;
    }
    public String getCategory(){
        return this.category;
    }

    public int getModelNumber() {
        return modelNumber;
    }

    @Override
    public void setCategory(String category) {
        this.category = category;
    }

}
