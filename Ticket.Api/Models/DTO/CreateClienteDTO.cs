namespace Ticket.Api.Models.DTO
{
    public class CreateClienteDTO
    {
        public string Nombres { get; set; } = string.Empty;
        public string Clave { get; set; } = string.Empty;
        public int ConfiguracionId { get; set; }
    }
}
