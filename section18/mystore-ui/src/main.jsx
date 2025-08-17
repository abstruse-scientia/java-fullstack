import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import About from "./components/About.jsx";
import Login, { loginAction } from "./components/Login.jsx";
import Contact from "./components/Contact.jsx";
import Cart from "./components/Cart.jsx";
import ErrorPage from "./components/ErrorPage.jsx";
import { CartProvider } from "./store/cart-context.jsx";
import { AuthProvider } from "./store/auth-context.jsx";
import ProtectedRoute from "./components/ProtectedRoute.jsx";
import Profile, {
  profileAction,
  profileLoader,
} from "./components/Profile.jsx";
import Orders, { ordersLoader } from "./components/Orders.jsx";
import AdminOrders, { adminOrdersLoader } from "./components/admin/AdminOrders.jsx";
import Messages, { messagesLoader } from "./components/admin/Messages.jsx";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";

const stripePromise = loadStripe(
  "pk_test_51RwT7fGhUlsPF3DY0tVPwXwvSaN1hVKgD7TVYoXkpolDDdewtw08b8qSYU0SjHHL14tlaiJoxWpyX6P2pK7qrZm400LMq7VgGP"
);

import {
  createBrowserRouter,
  RouterProvider,
  createRoutesFromElements,
  Route,
} from "react-router-dom";
import Home, { productsLoader } from "./components/Home.jsx";
import { contactAction } from "./components/Contact.jsx";

import { ToastContainer, Bounce } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import ProductDetail from "./components/ProductDetail.jsx";
import CheckoutForm from "./components/CheckoutForm.jsx";
import Register, { registerAction } from "./components/Register.jsx";
import OrderSuccess from "./components/OrderSuccess.jsx";

const routeDefinitions = createRoutesFromElements(
  <Route path="/" element={<App />} errorElement={<ErrorPage />}>
    <Route index element={<Home />} loader={productsLoader} />
    <Route path="/home" element={<Home />} loader={productsLoader} />
    <Route path="/about" element={<About />} />
    <Route path="/contact" element={<Contact />} action={contactAction} />
    <Route path="/login" element={<Login />} action={loginAction} />
    <Route path="/register" element={<Register />} action={registerAction} />

    <Route path="/cart" element={<Cart />} />
    <Route path="/products/:productId" element={<ProductDetail />} />
    <Route element={<ProtectedRoute />}>
      <Route path="/checkout" element={<CheckoutForm />} />
      <Route path="/order-success" element={<OrderSuccess />} />

      <Route
        path="/profile"
        element={<Profile />}
        loader={profileLoader}
        action={profileAction}
        shouldRevalidate={({ actionResult }) => {
          return !actionResult?.success;
        }}
      />
      <Route path="/orders" element={<Orders />} loader={ordersLoader}/>
      <Route path="/admin/orders" element={<AdminOrders />} loader={adminOrdersLoader}/>
      <Route path="/admin/messages" element={<Messages />} loader={messagesLoader}/>
    </Route>
  </Route>
);

const appRouter = createBrowserRouter(routeDefinitions);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Elements stripe={stripePromise}>
      <AuthProvider>
        <CartProvider>
          <RouterProvider router={appRouter} />
        </CartProvider>
      </AuthProvider>

      <ToastContainer
        position="top-center"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        draggable
        pauseOnHover
        theme={localStorage.getItem("theme") === "dark" ? "dark" : "light"}
        transition={Bounce}
      />
    </Elements>
  </StrictMode>
);
