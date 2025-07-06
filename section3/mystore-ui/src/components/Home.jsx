import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";
import products from "../data/products";
export default function Home(){
    return (
        <div className="home-container">
            <PageHeading title="LaptopStickz!">
                Add a touch to your laptop with our wide range of fun unique stickers.
                Perfect for any ocassion.
            </PageHeading>
            <ProductListings products={products}/>
        </div>

    );
}