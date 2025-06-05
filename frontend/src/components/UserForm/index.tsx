import "./styles.css";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from 'zod';

type Props = {
    isEditing: boolean,
}

const userSchema = z.object({
    name: z.string().nonempty("Campo requerido")
        .min(6, "O nome deve ter ao menos 6 caracteres"),
    cpf: z.string()
        .nonempty("Campo requerido.")
        .regex(/^(\d{3}\.\d{3}\.\d{3}\-\d{2}|\d{11})$/, "CPF no formato inválido"),
    cep: z.string().nonempty("Campo requerido.")
        .min(8, "O nome deve ter ao menos 8 dígitos"),
});

type UserSchema = z.infer<typeof userSchema>;

export default function UserForm({ isEditing }: Props) {

    const { register, handleSubmit, formState: { errors } } = useForm({
        resolver: zodResolver(userSchema)
    });

    function handleUserForm(data: UserSchema) {
        console.log(data);

    }

    return (
        <form onSubmit={handleSubmit(handleUserForm)}>
            <div className="form-input">
                <label>Nome</label>
                <input className={errors.name && "input-error"} type="text" placeholder="Digite seu Nome" {...register('name')} />
                {errors.name?.message && <p className="input-error-message">{errors.name?.message}</p>}
            </div>
            <div className="form-input">
                <label>CPF</label>
                <input className={errors.cpf && "input-error"} type="text" placeholder="Digite seu CPF"  {...register('cpf')} />
                {errors.cpf?.message && <p className="input-error-message">{errors.cpf?.message}</p>}
            </div>
            <div className="form-input">
                <label>CEP</label>
                <input className={errors.cep && "input-error"} type="text" placeholder="Digite seu CEP"  {...register('cep')} />
                {errors.cep?.message && <p className="input-error-message">{errors.cep?.message}</p>}
            </div>
            <div className="form-button">
                <button onClick={handleSubmit(handleUserForm)}>Salvar</button>
            </div>
        </form>
    );
}