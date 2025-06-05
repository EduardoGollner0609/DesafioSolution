import { useParams } from 'react-router-dom';
import './styles.css';
import UserForm from '../../../components/UserForm';

export default function FormPage() {

    const params = useParams();

    const userId = params.userId;

    const isEditing = userId !== "create";

    return (
        <main>
            <section id="form-page-section">
                <div className="container">
                    <div className="card-form">
                        <div className="form-space">
                            <h3>{isEditing ? `Atualizar usuário ${userId}` : "Criar usuário"}</h3>
                            <UserForm isEditing={isEditing} />
                        </div>
                        <div className="requisits-space">
                            <h3>Requisitos</h3>
                            <ul className="requisists-list">
                                <li>- Campo de nome, cpf e cep são obrigatórios</li>
                                <li>- O CPF deve possuir 11 digitos</li>
                                <li>- O CPF deve possuir 11 digitos</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    );
}