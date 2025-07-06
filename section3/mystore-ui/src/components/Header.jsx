import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTags, faShoppingBasket } from "@fortawesome/free-solid-svg-icons";
const Header = () => {
  return (
    <header className="header">
      <div className="container">
        <a href="/" className="link">
          <FontAwesomeIcon icon={faTags} className="fa-icon" />
          <span className="brand-name">LaptopStickz</span>
        </a>
        <nav className="nav">
          <ul>
            <li>
              <a href="/" className="nav-link">
                Home
              </a>
            </li>
            <li>
              <a href="/about" className="nav-link">
                About
              </a>
            </li>
            <li>
              <a href="/Contact" className="nav-link">
                Contact
              </a>
            </li>
            <li>
              <a href="/login" className="nav-link">
                Login
              </a>
            </li>
            <li>
              <a href="/cart" className="nav-link">
                <FontAwesomeIcon icon={faShoppingBasket} />
              </a>
            </li>
          </ul>
        </nav>
      </div>    
    </header>
  );
};

export default Header;
