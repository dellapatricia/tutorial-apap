import React from "react";
import Button from "../button";
import classes from "./styles.module.css";

const CartItem = (props) => {
const { id, title, price, description, category, quantity, handleEdit, handleDelete, handleAddtoCart, handleCartChange} = props;

    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Stok: ${quantity}`}</p>
            <p>{`Total Harga: ${quantity*price}`}</p>
        </div>
    );
};

export default CartItem;