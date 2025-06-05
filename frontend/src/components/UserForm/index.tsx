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

export default function UserForm({ id, isEditing }: Props) {

    const navigate = useNavigate();

    const { register, handleSubmit, reset, formState: { errors } } = useForm({
        resolver: zodResolver(userSchema)
    });

    const createUser = useCreateUserMutation();

    const updateUser = useUpdateUserMutation();

    const [errorMessage, setErrorMessage] = useState("");

    const findByIdUser = useUserByIdQuery(id!, {
        enabled: id != null
    });

    useEffect(() => {
        if (id != null && findByIdUser.data?.data) {
            reset({
                name: findByIdUser.data.data.name,
                cpf: findByIdUser.data.data.cpf,
                cep: findByIdUser.data.data.address.cep
            });
        }
    }, [id, findByIdUser.data]);

    function handleUserForm(data: UserSchema) {
        const requestDTO: UserRequestDTO = data;

        if (isEditing && id != null) {
            updateUser.mutate(
                { id, requestDTO },
                {
                    onSuccess: () => {
                        navigate("/address-list");
                    },
                    onError: (error) => {
                        if (error instanceof AxiosError) {
                            setErrorMessage(error.response?.data.error);
                        }
                    }
                }
            );
        } else {
            createUser.mutate(
                requestDTO,
                {
                    onSuccess: () => {
                        navigate("/address-list");
                    },
                    onError: (error) => {
                        if (error instanceof AxiosError) {
                            setErrorMessage(error.response?.data.error);
                        }
                    }
                }
            );
        }
    }


    return (
        <>
            {
                id != null && !findByIdUser.error && findByIdUser.isLoading &&
                <p className="loading">Carregando...</p>
            }
            {
                !findByIdUser.error && !findByIdUser.isLoading
                && <form onSubmit={handleSubmit(handleUserForm)}>
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
            }
            {
                findByIdUser.error && !findByIdUser.isLoading &&
                <p className="error-form-message">{`Usuário do id ${id} não foi encontrado`}</p>
            }
            {
                errorMessage.length > 0 &&
                <CardError message={errorMessage} onClose={() => setErrorMessage("")} />
            }
        </>
    );
}