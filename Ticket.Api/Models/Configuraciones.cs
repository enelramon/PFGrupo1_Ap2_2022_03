using System.ComponentModel.DataAnnotations;

namespace Ticket.Api.Models
{
    public class Configuraciones
    {
        [Key]
        public int ConfiguracionId { get; set; } = 1;
        public int Theme { get; set; } = 0; //1 = Light, 2= Dark, 0 = System
        public int ColorSchemeIndex { get; set; } = 0;
        public bool RecordarEnDispositivo { get; set; } = false;
    }
}
