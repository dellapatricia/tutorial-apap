import React from "react";
import classes from "./styles.module.css";
import Button from "../button";
 
const Item = (props) => {
    const { id, title, price, description, category, quantity, handleEdit, handleDelete, handleChangeField, addtoCard } = props;
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
                            className={classes.textField}
                            type="number"
                            placeholder="Insert quantity"
                            name="quantity"
                            onChange={handleChangeField}
                        />
            </form>
            <Button action={addtoCard}>
                Add To Car
            </Button>
            </div>
            
        </div>
    );
};

export default Item;