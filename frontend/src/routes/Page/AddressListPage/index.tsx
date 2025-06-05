import CardAddress from '../../../components/CardAddress';
import CardPlus from '../../../components/CardPlus';
import './styles.css';

export default function AddressListPage() {
    return (
        <main>
            <section id="address-list-section" className="container">
                <h2 className="address-list-title">Lista de endereços</h2>
                <div className="address-list">
                    <CardAddress id={1} name="Eduardo" cpf="19999999" cep="29072320"
                        street="Rua henrique martins tuche" neighboord="Segurança do Lar"
                        city="Vitória" state="Espirito Santo" />
                    <CardPlus />
                </div>
            </section>
        </main>
    );
}