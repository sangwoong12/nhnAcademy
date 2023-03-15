package org.example.pattern.factoryMethod;

public abstract class Factory {
    public final Product createProduct(int modelNumber, String modelName) {
        Product product = create(modelNumber, modelName);
        setCategory(product);
        return product;
    }
    protected abstract Product create(int modelNumber, String modelName);
    protected abstract void setCategory(Product product);
}