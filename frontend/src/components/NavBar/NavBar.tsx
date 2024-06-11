import { Link } from "react-router-dom";
import "./NavBar.css"

const NavBar:React.FC = () => {
    return (
        <nav className="nav">
            <Link to="/" className="site-title">Lights Out</Link>
            <ul>
                <Link to="/"className="active">Problems</Link>
                <Link to="/editor"className="active">Editor</Link>
            </ul>
        </nav>
    )
}


export default NavBar;