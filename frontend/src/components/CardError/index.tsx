import './styles.css';

type Props = {
    message: string,
    onClose: () => void
}

export default function CardError({ message, onClose }: Props) {
    return (
        <div className="background-card-error" onClick={onClose}>
            <div className="card-error" onClick={(e) => e.stopPropagation()}>
                <h3>Erro</h3>
                <p>{message}</p>
                <button className="button-close-card-error" onClick={onClose}>Fechar</button>
            </div>
        </div>
    );
}