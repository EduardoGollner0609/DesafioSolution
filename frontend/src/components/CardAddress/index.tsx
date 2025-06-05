import './styles.css';

type Props = {
    id: number;
    name: string;
    cpf: string;
    cep: string;
    street: string;
    neighboord: string;
    city: string;
    state: string;
}

export default function CardAddress({ id, name, cpf, cep, street, neighboord, city, state }: Props) {
    return (
        <div className="card-address card">
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
                <h5>Bairro:</h5> <p>{neighboord}</p>
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