import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Link } from "react-router-dom";
import { Fab } from "@material-ui/core";

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,
            isSearch: false,
            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,
            targetId: "",
            targetJumlah: 0,
            cartItems: []
        };
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleSubmitSearchItem = this.handleSubmitSearchItem.bind(this);
        this.handleSearchItem = this.handleSearchItem.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleDelete = this.handleDelete.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleAddtoCart = this.handleAddtoCart.bind(this);
        this.handleCartChange = this.handleCartChange.bind(this);
    }
    componentDidMount() {
        this.loadData();
        this.loadDataCart();
        console.log("componentDidMount()");
    }

    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }

    handleAddItem() {
        this.setState({ isCreate: true });
    }

    handleSearchItem() {
        this.setState({ isSearch: true });
        this.setState({ items:[] });
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit: false, isSearch: false });
    }

    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }

    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    handleCartChange(jumlah,idItem,quantity){
        this.setState({
            targetQuantity : quantity,
            targetId : idItem,
            targetJumlah : jumlah,
        })
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

    async handleAddtoCart(event){
        event.preventDefault();
        try {
            if (this.state.targetQuantity >= this.state.targetJumlah) {
            const data = {
                idItem: this.state.targetId,
                quantity : this.state.targetJumlah
            };
            await APIConfig.post("/cart", data);
            this.loadData();
            this.loadDataCart();
        }else{
            alert("Stok tidak cukup");
        }
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");

            console.log(error);
        }
        this.handleCancel(event);
    }

    async handleSubmitSearchItem(event){
        event.preventDefault();
        try {
            const title = this.state.title;
            console.log("masuk title")
            const { data } = await APIConfig.get(`/item?title=` + title)
            console.log("masuk data")
            this.setState({ items: data.result });
            console.log("masuk set")
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    async handleDelete(item) {
        try {
            const { data } = await APIConfig.delete("/item/" + item.id);
            alert("Berhasil delete item dengan id" + item.id)
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        return (
            <div className={classes.itemList}> <h1 className={classes.title}>
                All Items
            </h1>
            <Link to="/cart">
                <Fab variant="extended" onClick={this.handleToggle}>
                    <Badge
                        color="secondary"
                        badgeContent={this.state.cartItems.length}
                    >
                        <ShoppingCartIcon />
                    </Badge>
                </Fab>
            </Link>
                <form>
                    <input
                        className={classes.textField}
                        type="text"
                        placeholder="Cari Item"
                        name="title"
                        onChange={this.handleChangeField}
                    />
                    </form>
                    <Button action={this.handleSubmitSearchItem}>
                        Search
                    </Button>
                    <br></br>
                <br></br>
                    <Button action={this.handleAddItem}>
                    Add Item
                </Button>
                    {this.state.isSearch ? 
                <div >
                    {this.state.items.map((item) => (<Item
                        key={item.id}
                        id={item.id}
                        title={item.title} 
                        price={item.price} 
                        description={item.description} 
                        category={item.category} 
                        quantity={item.quantity}
                        handleEdit={() => this.handleEditItem(item)}
                        handleAddtoCart={this.handleAddtoCart}
                        handleCartChange={this.handleCartChange}
                        handleDelete={() => this.handleDelete(item)}
                    />
                    ))} </div>
              :
              <div >
                    {this.state.items.map((item) => (<Item
                        key={item.id}
                        id={item.id}
                        title={item.title} 
                        price={item.price} 
                        description={item.description} 
                        category={item.category} 
                        quantity={item.quantity}
                        handleEdit={() => this.handleEditItem(item)}
                        handleAddtoCart={this.handleAddtoCart}
                        handleCartChange={this.handleCartChange}
                        handleDelete={() => this.handleDelete(item)}
                    />
                    ))} </div>
                }
                <Modal
                    show={this.state.isCreate || this.state.isEdit}
                    handleCloseModal={this.handleCancel}
                    modalTitle={this.state.isCreate
                        ? "Add Item"
                        : `Edit Item ID ${this.state.id}`}
                >
                    <form>
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Nama Item"
                            name="title"
                            value={this.state.title}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="Harga"
                            name="price"
                            value={this.state.price}
                            onChange={this.handleChangeField}
                        />
                        <textarea
                            className={classes.textField}
                            placeholder="Deskripsi"
                            name="description"
                            rows="4"
                            value={this.state.description}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="text"
                            placeholder="Kategori"
                            name="category"
                            value={this.state.category}
                            onChange={this.handleChangeField}
                        />
                        <input
                            className={classes.textField}
                            type="number"
                            placeholder="qty"
                            name="quantity"
                            value={this.state.quantity}
                            onChange={this.handleChangeField}
                        />
                        <Button action={this.state.isCreate
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}
                        >
                            Create
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal> </div>
         
        );
    }


}
export default ItemList;