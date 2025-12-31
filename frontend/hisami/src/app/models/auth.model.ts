export interface AuthenticatedUser {
  authenticated: boolean;
  authorities: string[];
  name: string;
}
