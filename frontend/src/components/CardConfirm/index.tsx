import './styles.css';

type Props = {
    message: string,
    confirmFunction: () => void,
    onClose: () => void
}

export default function CardConfirm({ message, confirmFunction, onClose }: Props) {
    return (
        <div className="background-card-confirm" onClick={onClose}>
            <div className="card-confirm" onClick={(e) => e.stopPropagation()}>
                <h3>Atenção</h3>
                <p>{message}</p>
                <div className="card-confirm-buttons-space">
                    <button className="button-card-confirm" onClick={confirmFunction}>Confirmar</button>
                    <button className="button-card-confirm" onClick={onClose}>Fechar</button>
                </div>

            </div>
        </div>
    );
}