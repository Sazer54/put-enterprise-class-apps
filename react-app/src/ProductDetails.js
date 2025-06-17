import React from 'react';
import { useParams, Link } from 'react-router-dom';

const ProductDetails = ({ products }) => {
    const { id } = useParams();
    const filteredProducts = products.filter(product => product.id === parseInt(id));

    if (filteredProducts.length === 0) {
        return null;
    }

    const product = filteredProducts[0];

    return (
        <div>
            <h1>{product.title}</h1>
            <p>Category: {product.category}<br />
                Brand: {product.brand}<br />
                Description: {product.description}<br />
                Price: ${product.price}<br />
            </p>
            <img src={product.thumbnail} alt={product.title} />
            <br />
            <Link to="/">Back to product list</Link>
        </div>
    );
};

export default ProductDetails;