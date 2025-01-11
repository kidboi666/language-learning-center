/* eslint-disable @typescript-eslint/no-explicit-any */
'use server';

import { ActionResponse } from '@/app/actions/action-response';
import { Me } from '@/context/use-me';

export const signUpAction = async (
  prevState: unknown,
  formData: FormData,
): Promise<ActionResponse<Me>> => {
  const email = formData.get('email');
  const password = formData.get('password');

  try {
    const response = await fetch(
      `${process.env.NEXT_PUBLIC_SERVER_URL}/auth/signup`,
      {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          email,
          password,
        }),
      },
    );

    const data = await response.json();

    if (data.error) {
      return {
        status: 'error',
        error: data.error,
      };
    }

    return {
      status: 'success',
      data,
    };
  } catch (err) {
    if (err instanceof Error) {
      return {
        status: 'error',
        error: {
          message: err.message,
        },
      };
    }

    return {
      status: 'error',
      error: {
        message: 'An error occurred',
      },
    };
  }
};

export const logout = async (): Promise<ActionResponse<null>> => {
  try {
    const response = await fetch('http://localhost:8001/auth/logout', {
      method: 'POST',
      credentials: 'include',
    });
    const data = await response.json();

    if (data.error) {
      return {
        status: 'error',
        error: data.error,
      };
    }

    return {
      status: 'success',
      data: null,
    };
  } catch (err) {
    if (err instanceof Error) {
      return {
        status: 'error',
        error: {
          message: err.message,
        },
      };
    }

    return {
      status: 'error',
      error: {
        message: 'An error occurred',
      },
    };
  }
};
