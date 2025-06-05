import './styles.css';

type Props = {
    status: number | undefined,
    message: string,
    onClose: () => void
}

export default function CardError({ status, message, onClose }: Props) {
    return (
        <div className="background-card-error">
            <div className="card-error" onBlur={onClose}>
                <h3>Erro: {status}</h3>
                <p>{message}</p>
                <button className="button-close-card-error" onClick={onClose}>Fechar</button>
            </div>
        </div>
    );
}