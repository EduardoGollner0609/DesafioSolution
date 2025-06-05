import { Link } from 'react-router-dom';
import './styles.css';

export default function CardPlus() {
    return (
        <div className="card-plus">
            <Link to="/form-page" className="plus">+</Link>
        </div>
    );
}