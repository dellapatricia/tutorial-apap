import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import { Link } from "react-router-dom";
import CartItem from "../../components/cartItem"
import { ThirtyFpsSelect } from "@mui/icons-material";

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            isLoading: false,
            isCreate: false,
            isEdit: false,
            cartItems: []
        };
        this.checkout = this.checkout.bind(this);
    }
    componentDidMount() {
        this.loadDataCart();
        console.log("componentDidMount()");
    }

    async loadDataCart() {
        try {
            const { data } = await APIConfig.get("/cart");
            this.setState({ cartItems: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async checkout() {
        try {
            await APIConfig.get("/cart/checkout");
            this.loadDataCart();
            alert("Berhasil checkout!");
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        return (
            <div className={classes.itemList}> 
            <Link to="/">
                <Button>
                   Back
                </Button> 
            </Link>
            <h1 className={classes.title}>
                Cart Items
            </h1>
            <div>
                {this.state.cartItems.length > 0 && (
                    <Button action={this.checkout}>
                        Checkout
                    </Button> 
                )}
            </div>
            
            <div>
                {this.state.cartItems.map((item) => (<CartItem
                    key={item.id}
                    id={item.id}
                    title={item.item.title} 
                    price={item.item.price} 
                    description={item.item.description} 
                    category={item.item.category} 
                    quantity={item.quantity}
                />
                ))} </div>
                </div>
        );
    }


}
export default Cart;