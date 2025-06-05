import { Link } from 'react-router-dom';
import './styles.css';

export default function CardPlus() {
    return (
        <div className="card-plus">
            <Link to="/form-page/create" className="plus">+</Link>
            <p>Criar usuário</p>
        </div>
    );
}