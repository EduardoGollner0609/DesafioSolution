import { Link } from 'react-router-dom';
import './styles.css';

export default function HomePage() {
    return (
        <main>
            <section id="home-page-section" className="container">
                <div className="card-welcome card">
                    <h3>Seja bem vindo</h3>
                    <div className="card-welcome-text">
                        <p>A Address Manager é uma aplicação web desenvolvida durante um processo seletivo. </p>
                        <p> O desafio proposto foi criar uma aplicação que pudesse cadastrar usuários com seus endereços,
                            buscados pelo cep. Venha dar uma olhada</p>
                    </div>
                    <div className="card-welcome-link">
                        <Link to="/address-list">Ver</Link>
                    </div>
                </div>
            </section>
        </main>
    );
}