import products from "../data/products";
import PageHeading from "./PageHeading";
import ProductListings from "./ProductListings";

export default function Home() {
  return (
    <div className="max-w-[1152px] mx-auto px-6 py-8">
      <PageHeading title="Explore Eazy Stickers!">
        Add a touch to your laptop with our wide range of fun unique stickers.
        Perfect for any ocassion.
      </PageHeading>
      <ProductListings products={products} />
    </div>
  );
}
