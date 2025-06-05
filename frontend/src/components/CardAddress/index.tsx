import './styles.css';
import editIcon from '../../assets/edit-icon.svg';
import trashIcon from '../../assets/trash-icon.svg';
import { useNavigate } from 'react-router-dom';

type Props = {
    id: number;
    name: string;
    cpf: string;
    cep: string;
    street: string;
    neighborhood: string;
    city: string;
    state: string;
}

export default function CardAddress({ id, name, cpf, cep, street, neighborhood, city, state }: Props) {

    const navigate = useNavigate();

    return (
        <div className="card-address card">
            <div className="card-address-functions">
                <div className="card-address-function-update" onClick={() => navigate(`/form-page/${id}`)}>
                    <img src={editIcon} alt="Edit Icon" />
                </div>
                <div className="card-address-function-delete">
                    <img src={trashIcon} alt="Delete Icon" />
                </div>
            </div>
            <h4>Usuario {id}</h4>
            <div className="card-address-field">
                <h5>Nome: </h5> <p>{name}</p>
            </div>
            <div className="card-address-field">
                <h5>Cpf: </h5> <p>{cpf}</p>
            </div>
            <div className="card-address-field">
                <h5>Cep: </h5> <p>{cep}</p>
            </div>
            <div className="card-address-field">
                <h5>Logradouro: </h5> <p>{street}</p>
            </div>
            <div className="card-address-field">
                <h5>Bairro:</h5> <p>{neighborhood}</p>
            </div>
            <div className="card-address-field">
                <h5>Cidade: </h5> <p>{city}</p>
            </div>
            <div className="card-address-field">
                <h5>Estado: </h5> <p>{state}</p>
            </div>
        </div>
    );
}