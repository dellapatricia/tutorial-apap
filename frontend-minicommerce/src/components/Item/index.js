import React from "react";
import classes from "./styles.module.css";
import Button from "../button";
 
const Item = (props) => {
    const { id, title, price, description, category, quantity, handleEdit, handleDelete, handleChangeField, handleAddtoCart, handleCartChange } = props;
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Kuantitas: ${quantity}`}</p>
            <div>
            <Button action={handleEdit}>
                Edit
            </Button>
            <Button action={handleDelete}>
                Delete
            </Button>
            </div>
            <div>
            <form>
            <input
                type="number"
                placeholder="Insert quantity"
                name="quantity"
                onChange={(e) => handleCartChange(e.target.value, id, quantity)}
            />
            </form>
            <Button action={handleAddtoCart}>
                Add To Cart
            </Button>
            </div>
            
        </div>
    );
};

export default Item;