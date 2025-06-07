import './styles.css';
import CardAddress from '../../../components/CardAddress';
import CardPlus from '../../../components/CardPlus';
import type { UserResponseDTO } from '../../../models/user';
import { useUsersQuery } from '../../../hooks/useUsers';

export default function AddressListPage() {

    const { data: users, isLoading, error } = useUsersQuery();

    if (isLoading) return <p className="loading">Carregando...</p>;
    if (error) return <p className="error">Erro: {error.message}</p>;

    return (
        <main>
            <section id="address-list-section" className="container">
                <h2 className="address-list-title">Lista de endereços</h2>
                {
                    users?.data.length === 0 &&
                    <CardPlus />
                }
                <div className="address-list">
                    {
                        users && users.data.length > 0 && (
                            <>
                                {users.data.map((user: UserResponseDTO) => (
                                    <CardAddress
                                        key={user.id}
                                        id={user.id}
                                        name={user.name}
                                        cpf={user.cpf}
                                        cep={user.address.cep}
                                        street={user.address.street}
                                        neighborhood={user.address.neighborhood}
                                        city={user.address.city}
                                        state={user.address.state}
                                    />
                                ))}
                                <CardPlus />
                            </>
                        )
                    }
                </div>
            </section>
        </main >
    );
}