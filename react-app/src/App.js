import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import ProductList from './ProductList';
import './App.css';
import ProductDetails from "./ProductDetails";

function App() {
    const [products, setProducts] = useState([]);

    useEffect(() => {
        axios.get('https://dummyjson.com/products')
            .then(response => {
                setProducts(response.data.products);
            })
            .catch(error => {
                console.error('Error fetching products:', error);
            });
    }, []);

    const router = createBrowserRouter([
        {
            path: '/',
            element: <ProductList products={products} />,
        },
        {
            path: '/products/:id',
            element: <ProductDetails products={products} />,
        },
    ]);

    return (
        <div className="App">
            <RouterProvider router={router} />
        </div>
    );
}

export default App;