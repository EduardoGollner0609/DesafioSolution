import './styles.css';

export default function FormPage() {
    return (
        <main>
            <section id="form-page-section">
                <div className="container">
                    <div className="card-form">
                        <div className="form-space">
                            <h3>Criar usuário</h3>
                            <form action="">
                                <div className="form-input">
                                    <label>Nome</label>
                                    <input type="text" />
                                </div>
                                <div className="form-input">
                                    <label>CPF</label>
                                    <input type="text" />
                                </div>
                                <div className="form-input">
                                    <label>CEP</label>
                                    <input type="text" />
                                </div>
                                <div className="form-button">
                                    <button>Criar</button>
                                </div>
                            </form>
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