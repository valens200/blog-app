import { useEffect } from "react";

export interface HeaderControllerProps {
  title?: string;
}

export const HeaderController: React.FC<HeaderControllerProps> = ({
  title,
}) => {
  useEffect(() => {
    document.title = `${title} / Blog` || "/Blog";
  }, []);
  return null;
};
