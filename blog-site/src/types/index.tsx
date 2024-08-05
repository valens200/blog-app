export type LayoutProps = {
  links: {
    name: string;
    url: string;
    image: unknown;
    alternativeImage: unknown;
  }[];
  children: React.ReactNode;
};

export interface IMember {
  firstName: string;
  lastName: string;
  username: string;
  type: "trainer" | "trainee";
}
export interface StatsCardProps {
  title: string;
  value: string;
  color: string;
  link?: {
    href: string;
    label: string;
  };
}

export interface Fields {
  name: string;
  placeholder: string;
  onchange: (e: unknown) => never;
}

export interface ErrorResponse {
  success: boolean;
  message: string;
  data: unknown;
}

export enum UserRole {
  USER = "USER",
  ADMIN = "ADMIN",
}

export interface Profile {
  profilePicture?: string;
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  nationalId: string;
  createdAt: string;
}
