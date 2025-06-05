import { useQuery, useMutation, useQueryClient } from "@tanstack/react-query";
import {
  create,
  findAll,
  findById,
  update,
  deleteById,
} from "../services/user-service.ts";

export function useUsersQuery() {
  return useQuery({
    queryKey: ["users"],
    queryFn: findAll,
  });
}

export function useUserByIdQuery(id: number) {
  return useQuery({
    queryKey: ["user", id],
    queryFn: () => findById(id),
    enabled: !!id,
  });
}

export function useCreateUserMutation() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: create,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });
}

export function useUpdateUserMutation() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: update,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });
}

export function useDeleteUserMutation() {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: deleteById,
    onSettled: () => {
      queryClient.invalidateQueries({ queryKey: ["users"] });
    },
  });
}
