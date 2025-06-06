import { useParams } from 'react-router-dom';
import './styles.css';
import UserForm from '../../../components/UserForm';

export default function FormPage() {

    const params = useParams();

    const isEditing = params.userId !== "create";

    const userId = Number(params.userId);

    return (
        <main>
            <section id="form-page-section">
                <div className="container">
                    <div className="card-form">
                        <div className="form-space">
                            <h3>{isEditing ? `Atualizar usuário ${userId}` : "Criar usuário"}</h3>
                            <UserForm id={userId ? userId : null} isEditing={isEditing} />
                        </div>
                        <div className="requisits-space">
                            <h3>Requisitos</h3>
                            <ul className="requisists-list">
                                <li>- Campo de nome, CPF e CEP são obrigatórios.</li>
                                <li>- O CPF deve possuir 11 digitos seja com ou sem pontuação.</li>
                                <li>- O nome deve possuir no minímo 6 caracteres</li>
                                <li>- O CEP deve possuir no minímo 8 caracteres</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </section>
        </main>
    );
}