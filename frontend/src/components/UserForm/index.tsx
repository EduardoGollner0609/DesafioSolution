import "./styles.css";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { z } from 'zod';
import { useCreateUserMutation, useUpdateUserMutation, useUserByIdQuery } from "../../hooks/useUsers";
import type { UserRequestDTO } from "../../models/user";
import { useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import CardError from "../CardError";
import { AxiosError } from "axios";

type Props = {
    id: number | null
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
// ...imports...

export default function UserForm({ id, isEditing }: Props) {
    const navigate = useNavigate();

    const { register, handleSubmit, reset, formState: { errors } } = useForm<UserSchema>({
        resolver: zodResolver(userSchema)
    });

    const createUser = useCreateUserMutation();
    const updateUser = useUpdateUserMutation();
    const [errorMessage, setErrorMessage] = useState("");

    const { data: user, isError, isLoading } = useUserByIdQuery(id!, {
        enabled: id != null
    });

    useEffect(() => {
        if (id != null && user?.data?.data) {
            reset({
                name: user.data.data.name,
                cpf: user.data.data.cpf,
                cep: user.data.data.address.cep
            });
        }
    }, [id, user]);

    useEffect(() => {
        if (isError) navigate("/");
    }, [isError]);

    const handleUserForm = (data: UserSchema) => {
        const requestDTO: UserRequestDTO = data;

        const onSuccess = () => navigate("/address-list");
        const onError = (error: unknown) => {
            if (error instanceof AxiosError) {
                setErrorMessage(error.response?.data?.error || "Erro ao salvar usuário.");
            }
        };

        if (isEditing && id != null) {
            updateUser.mutate({ id, requestDTO }, { onSuccess, onError });
        } else {
            createUser.mutate(requestDTO, { onSuccess, onError });
        }
    };

    const isFormLoading = (id != null && isLoading) || createUser.isPending || updateUser.isPending;

    return (
        <>
            {isFormLoading && <p className="loading">Carregando...</p>}
            {!isFormLoading &&
                <form onSubmit={handleSubmit(handleUserForm)}>
                    <div className="form-input">
                        <label>Nome</label>
                        <input autoComplete="off" className={errors.name && "input-error"} type="text" placeholder="Digite seu Nome" {...register('name')} />
                        {errors.name?.message && <p className="input-error-message">{errors.name.message}</p>}
                    </div>
                    <div className="form-input">
                        <label>CPF</label>
                        <input autoComplete="off" className={errors.cpf && "input-error"} type="text" placeholder="Digite seu CPF" {...register('cpf')} />
                        {errors.cpf?.message && <p className="input-error-message">{errors.cpf.message}</p>}
                    </div>
                    <div className="form-input">
                        <label>CEP</label>
                        <input autoComplete="off" className={errors.cep && "input-error"} type="text" placeholder="Digite seu CEP" {...register('cep')} />
                        {errors.cep?.message && <p className="input-error-message">{errors.cep.message}</p>}
                    </div>
                    <div className="form-button">
                        <button type="submit">Salvar</button>
                    </div>
                </form>
            }
            {errorMessage &&
                <CardError message={errorMessage} onClose={() => setErrorMessage("")} />
            }
        </>
    );
}
